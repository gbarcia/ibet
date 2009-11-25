package ve.edu.ucab.ibet.servicios.impl;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.generic.util.mail.interfaces.IMailService;
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

    public Boolean ActualizarMontoMaximo(Double nuevoMonto, MedioPago medioPago) {
        List<UsuarioMedioPago> historial = new ArrayList<UsuarioMedioPago>();
        String query = new String();
        Object[] o = new Object[2];

//        o[0] = security.getName();
        o[0] = "maya";
        o[1] = medioPago.getId();

        query = "select ump " +
                "from Users u inner join u.usuarioMedioPagoCollection as ump " +
                "where ump.users.username = ? " +
                "and ump.medioPago.id = ? " +
                "and ump.fechaFin is null ";

        historial.addAll(genericDao.ejecutarQueryList(query, o));

        UsuarioMedioPago usuario = new UsuarioMedioPago();
        
        usuario.setUsuarioMedioPagoPK(historial.get(0).getUsuarioMedioPagoPK());
        usuario.setActivo(historial.get(0).getActivo());
        usuario.setFechaInicio(historial.get(0).getFechaInicio());
        usuario.setFechaFin(new java.util.Date());
        usuario.setMedioPago(historial.get(0).getMedioPago());
        usuario.setMontoMaximo(historial.get(0).getMontoMaximo());

        
        return Boolean.TRUE;
    }

    public Boolean ActivarMedioPago(MedioPago medioPago) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Boolean DesactivarMedioPago(MedioPago medioPago) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<MedioPago> mostrarHistorialMedioPago() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void enviarCorreoNotificacion() {
        throw new UnsupportedOperationException("Not supported yet.");
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

}
