package ve.edu.ucab.ibet.servicios.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.annotation.Secured;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPagoPK;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.generic.util.mail.interfaces.IMailService;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuarioMedioPago;

/**
 * 
 * @author maya
 */
public class ServicioUsuarioMedioPagoImpl implements IServicioUsuarioMedioPago {

    private IGenericDao genericDao;
    private IHelperProperties helperProperties;
    private IMailService servicioMail;
    private Principal security;
    private IServicioUsuario servicioUsuario;

    private UsuarioMedioPago obtenerUsuarioMedioPago(MedioPago medioPago, Users user) {
        UsuarioMedioPago userMedioPago = new UsuarioMedioPago();

        String query = new String();
        Object[] o = new Object[2];

        o[0] = user.getUsername();
        o[1] = medioPago.getId();

        query = "select ump " +
                "from Users u inner join u.usuarioMedioPagoCollection as ump " +
                "where ump.users.username = ? " +
                "and ump.medioPago.id = ? " +
                "and ump.fechaFin is null ";

        userMedioPago = (UsuarioMedioPago) genericDao.ejecutarQueryUnique(query, o);

        return userMedioPago;
    }

    private void insertarUsuarioMedioPago(MedioPago medioPago, Double montoMaximo, Users user) {

        UsuarioMedioPago usuarioMedioPago = new UsuarioMedioPago();

        UsuarioMedioPagoPK pk = new UsuarioMedioPagoPK();
        pk.setIdMedioPago(medioPago.getId());
        pk.setUsername(user.getUsername());

        usuarioMedioPago.setUsuarioMedioPagoPK(pk);
        usuarioMedioPago.setActivo(Boolean.TRUE);
        usuarioMedioPago.setFechaInicio(UtilMethods.convertirFechaFormato(new java.util.Date()));
        usuarioMedioPago.setFechaFin(null);
        usuarioMedioPago.setMedioPago(medioPago);
        usuarioMedioPago.setMontoMaximo(montoMaximo);

        genericDao.insertar(usuarioMedioPago);
    }

    private void mergeUsuarioMedioPago(UsuarioMedioPago userMedioPago) {
        UsuarioMedioPago usuarioMedioPago = new UsuarioMedioPago();

        usuarioMedioPago.setUsuarioMedioPagoPK(userMedioPago.getUsuarioMedioPagoPK());
        usuarioMedioPago.setActivo(Boolean.FALSE);
        usuarioMedioPago.setFechaInicio(userMedioPago.getFechaInicio());
        usuarioMedioPago.setFechaFin(UtilMethods.convertirFechaFormato(new java.util.Date()));
        usuarioMedioPago.setMedioPago(userMedioPago.getMedioPago());
        usuarioMedioPago.setMontoMaximo(userMedioPago.getMontoMaximo());

        genericDao.merge(usuarioMedioPago);
    }

    private Boolean esNulo(UsuarioMedioPago userMedioPago) {
        Boolean flag = Boolean.TRUE;

        if (userMedioPago == null) {

            flag = Boolean.TRUE;
            return flag;

        } else if (userMedioPago != null) {

            flag = Boolean.FALSE;
            return flag;
        }

        return flag;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void ActualizarMontoMaximo(Double nuevoMonto, MedioPago medioPago, Users user) {
        UsuarioMedioPago userMedioPago = this.obtenerUsuarioMedioPago(medioPago, user);

        this.mergeUsuarioMedioPago(userMedioPago);

        this.insertarUsuarioMedioPago(medioPago, nuevoMonto, user);
    }

    public void ActivarMedioPago(MedioPago medioPago, Double montoMaximo, Users user) {
        UsuarioMedioPago userMedioPago = this.obtenerUsuarioMedioPago(medioPago, user);

        if (this.esNulo(userMedioPago)) {

            this.insertarUsuarioMedioPago(medioPago, montoMaximo, user);

        } else if (!this.esNulo(userMedioPago)) {
            if (userMedioPago.getActivo()) {

                throw new ExcepcionNegocio("errors.mediopago.yaestaactivo");

            }
        }
    }

    public void DesactivarMedioPago(MedioPago medioPago, Users user) {
        UsuarioMedioPago userMedioPago = this.obtenerUsuarioMedioPago(medioPago, user);

        if (!this.esNulo(userMedioPago)) {

            this.mergeUsuarioMedioPago(userMedioPago);

        } else if (this.esNulo(userMedioPago)) {

            throw new ExcepcionNegocio("errors.mediopago.yaestadesactivo");

        }
    }

    @SuppressWarnings("unchecked")
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public List<UsuarioMedioPago> mostrarHistorialMedioPago(Users user) {
        List<UsuarioMedioPago> historial = new ArrayList<UsuarioMedioPago>();

        Object[] o = new Object[1];
        o[0] = user.getUsername();

        String query = new String();
        query = "select ump " +
                "from Users u inner join u.usuarioMedioPagoCollection as ump " +
                "where ump.users.username = ? ";

        historial.addAll(genericDao.ejecutarQueryList(query, o));

        return historial;
    }

    public void enviarCorreoNotificacion(UsuarioMedioPago usuarioMedioPago, Users user) {
        user = servicioUsuario.obtenerDatosUsuarioM(user.getUsername());

        String estado = (usuarioMedioPago.getActivo()) ? "Activado":"Desactivado";

        List<String> datosCorreo = new ArrayList<String>();
        String titulo = (user.getSexo().equalsIgnoreCase("M")) ? "Sr" : "Sra";
        datosCorreo.add(titulo);
        datosCorreo.add(user.getNombre() + " " + user.getApellido());
        datosCorreo.add(usuarioMedioPago.getMedioPago().getNombre());
        datosCorreo.add(estado);
        datosCorreo.add(String.valueOf(usuarioMedioPago.getMontoMaximo()));
        String asunto = helperProperties.getString("correos.notificacion.mediopago.asunto");
        String cuerpo = ("<p>" + helperProperties.getString("correos.notificacion.mediopago.mensaje.linea1", datosCorreo) + "<p>");
        cuerpo += ("<p>" + helperProperties.getString("correos.notificacion.mediopago.mensaje.linea2", datosCorreo) + "<p>");
        cuerpo += ("<p>" + helperProperties.getString("correos.notificacion.mediopago.mensaje.linea3", datosCorreo) + "<p>");
        cuerpo += ("<p>" + helperProperties.getString("correos.notificacion.mediopago.mensaje.linea4", datosCorreo) + "<p>");
        cuerpo += ("<p>" + helperProperties.getString("correos.notificacion.mediopago.mensaje.linea5") + "<p>");
        cuerpo += ("<p>" + helperProperties.getString("correos.notificacion.mediopago.mensaje.linea6") + "<p>");
        
        servicioMail.send(user.getCorreo(), asunto, cuerpo);
    }

    public IGenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(IGenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public IHelperProperties getHelperProperties() {
        return helperProperties;
    }

    public void setHelperProperties(IHelperProperties helperProperties) {
        this.helperProperties = helperProperties;
    }

    public IMailService getServicioMail() {
        return servicioMail;
    }

    public void setServicioMail(IMailService servicioMail) {
        this.servicioMail = servicioMail;
    }

    public Principal getSecurity() {
        return security;
    }

    public void setSecurity(Principal security) {
        this.security = security;
    }

    public IServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(IServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    
}
