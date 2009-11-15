package ve.edu.ucab.ibet.servicios.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.to.forms.RegistroUsuarioTO;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
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

    public boolean existeUsuarioM(Users u) {
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

    private boolean esUsuarioMayorEdad(Users u) {
        boolean esMayor = false;
        Integer anioActual = UtilMethods.extraerAnio(new Date());
        Integer anioUsuario = UtilMethods.extraerAnio(u.getFechaNacimiento());
        Integer resultado = anioActual - anioUsuario;
        if (resultado >= 18) {
            esMayor = true;
        }
        return esMayor;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void registroNuevoUsuarioM(Users user) {
        if (!existeUsuarioM(user)) {
            if (esUsuarioMayorEdad(user)) {
                user.setEnabled(false);
                user.setConfirmado(false);
                genericDao.insertar(user);
                enviarCorreo(user);
            } else {
                throw new ExcepcionNegocio(helperProp.getString("error.negocio.usuariomenor"));
            }
        } else {
            throw new ExcepcionNegocio(helperProp.getString("error.negocio.usuariorepetido"));
        }
    }

    public String obtenerAtributoError(String mensaje) {
        String resultado = null;
        if (mensaje.equals(helperProp.getString("error.negocio.usuariomenor"))) {
            resultado = "fechaNacimiento";
        } else if (mensaje.equals(helperProp.getString("error.negocio.usuariorepetido"))) {
            resultado = "nombreUsuario";
        }
        return resultado;
    }

    public Users obtenerDatosUsuarioM(String username) {
        Users user = null;
        user = (Users) genericDao.findByPropertyUnique(Users.class, "username", username);
        if (user == null) {
            throw new ExcepcionNegocio(helperProp.getString("error.negocio.usuarionoexiste"));
        }
        return user;

    }

    public void actualizarDatosUsuarioM(Users user) {
        genericDao.merge(user);
    }

    public Users transferObjectToModel(RegistroUsuarioTO to) {
        Users usuario = null;
        if (to != null) {
            usuario = new Users();
            usuario.setUsername(to.getNombreUsuario());
            usuario.setPassword(to.getClave());
            usuario.setNombre(to.getNombre());
            usuario.setApellido(to.getApellido());
            usuario.setFechaNacimiento(to.getFechaNacimiento());
            usuario.setCorreo(to.getCorreo());
            usuario.setSexo(to.getSexo());
            usuario.setTelefono(to.getTelefono());
            usuario.setCalle(to.getCalle());
            usuario.setCiudad(to.getCiudad());
            usuario.setCodigoPostal(to.getCodigoPostal());
            usuario.setEstado(to.getEstado());
            usuario.setPais(to.getPais());
        }
        return usuario;
    }
}