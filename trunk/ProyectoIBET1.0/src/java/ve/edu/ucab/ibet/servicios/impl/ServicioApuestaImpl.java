package ve.edu.ucab.ibet.servicios.impl;

import java.util.List;
import ve.edu.ucab.ibet.dominio.Evento;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioApuesta;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 * Clase que ofrece servicios para el manejo de apuestas
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ServicioApuestaImpl implements IServicioApuesta {

    private IGenericDao genericDao;
    private IServicioUsuario servicioUsuario;
    private IHelperProperties helperProp;

    public IGenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(IGenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public IHelperProperties getHelperProp() {
        return helperProp;
    }

    public void setHelperProp(IHelperProperties helperProp) {
        this.helperProp = helperProp;
    }

    public IServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(IServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    public void esValidaApuestaUsuario(Users usuario, TableroGanancia tablero) {
        if (usuarioHaApostadoEvento(usuario, tablero.getEvento())) {
            throw new ExcepcionNegocio("errors.apuesta.usuario.apuestadublicada");
        }
        else if (!usuarioPoseeMetodosPago(usuario)) {
            throw new ExcepcionNegocio("errors.apuesta.usuario.nometodospago");
        }
    }

    public void realizarApuesta(Users usuario, TableroGanancia tablero) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void esvalidaApuestaCasa(TableroGanancia tablero) {
    }

    @SuppressWarnings("unchecked")
    private Boolean usuarioHaApostadoEvento(Users usuario, Evento evento) {
        Boolean resultado = Boolean.TRUE;
        Object[] o = new Object[2];
        String query = new String();

        o[0] = usuario.getUsername();
        o[1] = evento.getId();
        query = "Select ac from Users u inner join u.apuestaCollection as ac where u.username =?" +
                "and ac.tableroGanancia.tableroGananciaPK.idEvento = ?";
        List<Evento> eventosResultado = genericDao.ejecutarQueryList(query, o);
        if (eventosResultado.isEmpty()) {
            resultado = Boolean.FALSE;
        }
        return resultado;
    }

    private Boolean usuarioPoseeMetodosPago(Users usuario) {
        Boolean resultado = Boolean.TRUE;
        List<UsuarioMedioPago> listaMetodosPago = servicioUsuario.obtenerMediosPagoVigenteUsuario(usuario);
        if (listaMetodosPago.isEmpty()) {
            resultado = Boolean.FALSE;
        }
        return resultado;
    }

    private Boolean montoMaximoPagoUsuarioAprobado (Users usuario, MedioPago medio, Double montoApostado) {
        Boolean resultado = Boolean.FALSE;
        Object[] o = new Object[2];
        String query = new String();
        o[0] = usuario.getUsername();
        o[1] = medio.getId();
        query ="select ump from Users u inner join u.usuarioMedioPagoCollection as ump " +
                "where ump.usuarioMedioPagoPK.username=? and ump.medioPago.id = ?";
        UsuarioMedioPago userMedioPago = (UsuarioMedioPago) genericDao.ejecutarQueryUnique(query, o);
        resultado = (montoApostado <= userMedioPago.getMontoMaximo()) ? Boolean.TRUE : Boolean.FALSE;
        return resultado;
    }
}
