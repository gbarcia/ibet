package ve.edu.ucab.ibet.servicios.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import ve.edu.ucab.ibet.dominio.Apuesta;
import ve.edu.ucab.ibet.dominio.ApuestaPK;
import ve.edu.ucab.ibet.dominio.Evento;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.dominio.Politica;
import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioApuesta;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioEvento;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 * Clase que ofrece servicios para el manejo de apuestas
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ServicioApuestaImpl implements IServicioApuesta {

    private IGenericDao genericDao;
    private IServicioUsuario servicioUsuario;
    private IServicioEvento servicioEvento;
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

    public IServicioEvento getServicioEvento() {
        return servicioEvento;
    }

    public void setServicioEvento(IServicioEvento servicioEvento) {
        this.servicioEvento = servicioEvento;
    }

    public void esValidaApuestaUsuario(Users usuario, TableroGanancia tablero) {
        if (usuarioHaApostadoEvento(usuario, tablero.getEvento())) {
            throw new ExcepcionNegocio("errors.apuesta.usuario.apuestadublicada");
        } else if (!usuarioPoseeMetodosPago(usuario)) {
            throw new ExcepcionNegocio("errors.apuesta.usuario.nometodospago");
        }
    }

    public void realizarApuesta(Apuesta apuesta) {
        if (apuesta != null) {
        esValidaApuestaUsuario(apuesta.getUsers(), apuesta.getTableroGanancia());
        esvalidaApuestaCasa(apuesta.getTableroGanancia(), apuesta.getMonto(), apuesta.getMedioPago(),apuesta.getUsers());
        ApuestaPK apuestaPK = new ApuestaPK();
        apuestaPK.setIdMedioPago(apuesta.getMedioPago().getId());
        apuestaPK.setUsername(apuesta.getUsers().getUsername());
        apuesta.setApuestaPK(apuestaPK);
        genericDao.insertar(apuesta);
        }
        else {
            throw new ExcepcionNegocio("errors.apuesta.invalida");
        }
    }

    private void esvalidaApuestaCasa(TableroGanancia tablero, Double montoApostado, MedioPago medioPago, Users usuario) {
        Evento eventoApuesta = servicioEvento.obtenerEventoporTableroGanancia(tablero);
        Politica politicaEvento = servicioEvento.obtenerPoliticaParaEvento(eventoApuesta);
        Double montoMaximoPermitido = politicaEvento.getMontoMaximo();
        Boolean casaPuedeFinalizar = politicaEvento.getFinalizarAntes();

        Double sumaActualApuestas = obtenerCantidadApostadaParaEvento(tablero);
        Double montoActualAcumulado = sumaActualApuestas + montoApostado;

        if (!montoMaximoPagoUsuarioAprobado(usuario, medioPago, montoApostado)) {
            throw new ExcepcionNegocio("errors.apuesta.montomaximo.metodopago.exedido");
        }
        if (montoActualAcumulado >= montoMaximoPermitido) {
            throw new ExcepcionNegocio("errors.apuesta.limitemontoexedido");
        }
        if (casaPuedeFinalizar) {
            if (!eventoApuesta.getEstatus()) {
                throw new ExcepcionNegocio("errors.apuesta.eventocerrado");
            }
        }
        if (!esPeriodoDeApuestaVigente(tablero)) {
            throw new ExcepcionNegocio("erros.apuesta.cerradatiempo");
        }
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

    private Boolean montoMaximoPagoUsuarioAprobado(Users usuario, MedioPago medio, Double montoApostado) {
        Boolean resultado = Boolean.FALSE;
        Object[] o = new Object[2];
        String query = new String();
        o[0] = usuario.getUsername();
        o[1] = medio.getId();
        query = "select ump from Users u inner join u.usuarioMedioPagoCollection as ump " +
                "where ump.usuarioMedioPagoPK.username=? and ump.medioPago.id = ?";
        UsuarioMedioPago userMedioPago = (UsuarioMedioPago) genericDao.ejecutarQueryUnique(query, o);
        resultado = (montoApostado <= userMedioPago.getMontoMaximo()) ? Boolean.TRUE : Boolean.FALSE;
        return resultado;
    }

    private Boolean esPeriodoDeApuestaVigente(TableroGanancia tablero) {
        Boolean resultado = Boolean.FALSE;
        Evento eventoAconsultar = servicioEvento.obtenerEventoporTableroGanancia(tablero);
        Date fechaMaxEvento = UtilMethods.convertirFechaFormato(eventoAconsultar.getFechaMaxima());
        Date fechaActual = UtilMethods.convertirFechaFormato(new Date());
        Date horaMaxEvento = eventoAconsultar.getHoraMaxima();
        Calendar horaMaxApuesta = Calendar.getInstance();
        horaMaxApuesta.setTime(horaMaxEvento);
        Calendar horaActual = Calendar.getInstance();
        Integer horaActualNumber = horaActual.get(Calendar.HOUR_OF_DAY);
        Integer horaMaxEventoNumber = horaMaxApuesta.get(Calendar.HOUR_OF_DAY);
        if (fechaActual.after(fechaMaxEvento)) {
            return Boolean.FALSE;
        }
        if (horaActualNumber < horaMaxEventoNumber) {
            resultado = Boolean.TRUE;
        }
        return resultado;
    }

    private Double obtenerCantidadApostadaParaEvento(TableroGanancia tablero) {
        String query = new String();
        Evento evento = servicioEvento.obtenerEventoporTableroGanancia(tablero);
        Object[] parametros = {evento.getId()};
        query = "Select New java.lang.Double (Sum(a.monto)) from Apuesta a where a.tableroGanancia.evento.id =? " +
                "group by a.tableroGanancia.evento.nombre";
        Double monto = (Double) genericDao.ejecutarQueryUnique(query, parametros);
        return monto;
    }
}
