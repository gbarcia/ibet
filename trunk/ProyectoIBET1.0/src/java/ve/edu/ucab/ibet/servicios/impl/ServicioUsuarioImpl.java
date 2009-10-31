package ve.edu.ucab.ibet.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.generic.excepciones.bd.ExcepcionBaseDatos;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.generic.util.mail.interfaces.IMailService;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 * Servicio para trabajar con usuarios
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
//        String asunto = helperProp.getString("correos.plantillas.asunto.registro");
        String cuerpo = "";
//        cuerpo =  ("<p>" + helperProp.getString("correos.plantillas.cuerpo.encabezado", datosCorreo) + "</p>");
//        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea1") + "</p>");
//        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea2") + "</p>");
//        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea3") + "</p>");
//        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea4", datosCorreo) + "</p>");
//        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea5", datosCorreo) + "</p>");
//        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea6") + "</p>");
//        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea7") + "</p>");
//        cuerpo += ("<p>" + helperProp.getString("correos.plantillas.cuerpo.mensaje.linea8") + "</p>");

        servicioMail.send(user.getCorreo(), "a", "prueba");
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = ExcepcionBaseDatos.class)
    public boolean registroNuevoUsuarioM(Users user) throws GeneralException {
        boolean resultado = false;
        try {
            user.setEnabled(false);
            user.setConfirmado(false);
            genericDao.insertar(user);
            enviarCorreo(user);
            resultado = true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcepcionBaseDatos(e, helperProp.getString("servicios.serviciousuario.excepciones.bd.insert"));
        }finally{
            return resultado;
        }
    }
}
