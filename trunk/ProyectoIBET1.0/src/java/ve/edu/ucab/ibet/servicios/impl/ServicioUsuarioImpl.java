package ve.edu.ucab.ibet.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.generic.excepciones.bd.ExcepcionBaseDatos;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.generic.util.mail.interfaces.IMailService;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 * Clase que ofrece servicios para el manejo de usuarios
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ServicioUsuarioImpl implements IServicioUsuario {

    private IMailService servicioMail;
    private IGenericDao genericDao;
    private IHelperProperties helperProp;

    public IHelperProperties getHelperProp() {
        return helperProp;
    }

    public void setHelperProp(IHelperProperties helperProp) {
        this.helperProp = helperProp;
    }

    public IGenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(IGenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public IMailService getServicioMail() {
        return servicioMail;
    }

    public void setServicioMail(IMailService servicioMail) {
        this.servicioMail = servicioMail;
    }

    private void enviarCorreo(Users user) {
        List<String> datosCorreo = new ArrayList<String>();
        String titulo = (user.getSexo().equalsIgnoreCase("M")) ? "Sr" : "Sra";
        datosCorreo.add(titulo);
        datosCorreo.add(user.getNombre() + " " + user.getApellido());
        datosCorreo.add(user.getUsername());
        datosCorreo.add(user.getPassword());
        String asunto = helperProp.getString("correos.plantillas.asunto.registro");
        String cuerpo = ("<p>" + helperProp.getString("correos.plantillas.cuerpo.encabezado", datosCorreo) + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea1") + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea2") + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea3") + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea4", datosCorreo) + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea5", datosCorreo) + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea6") + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea7") + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea8") + "</p>");

        servicioMail.send(user.getCorreo(), asunto, cuerpo);
    }

    public boolean existeUsuario(Users u) {
        boolean resultado = true;
        Users usuarioEnBd = (Users) genericDao.findByPropertyUnique(Users.class, "username", u.getUsername());
        if (usuarioEnBd == null) {
            usuarioEnBd = (Users) genericDao.findByPropertyUnique(Users.class, "correo", u.getCorreo());
            if (usuarioEnBd == null) {
                resultado = false;
            }
        }
        return resultado;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean registroNuevoUsuarioM(Users user) throws GeneralException {
        boolean resultado = false;
        try {
            if (!existeUsuario(user)) {
                user.setEnabled(false);
                user.setConfirmado(false);
                genericDao.insertar(user);
                enviarCorreo(user);
                resultado = true;
            } else {
                throw new ExcepcionNegocio(helperProp.getString("servicios.serviciousuario.excepciones.bd.insert"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcepcionBaseDatos(e, helperProp.getString("servicios.serviciousuario.excepciones.bd.insert"));
        } finally {
            return resultado;
        }
    }

    public Users obtenerDatosUsuarioM(String username) throws GeneralException {
        Users user = null;
        try {
            user = (Users) genericDao.findByPropertyUnique(Users.class, "username", username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcepcionBaseDatos(e, helperProp.getString("gpu.error.basededatos.obtenerusuarios"));
        } finally {
            return user;
        }

    }

    public void actualizarDatosUsuarioM(Users user) throws GeneralException {
        try {
            genericDao.merge(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcepcionBaseDatos(e, helperProp.getString("gpu.error.basededatos.actualizarusuarios"));
        }
    }
}
