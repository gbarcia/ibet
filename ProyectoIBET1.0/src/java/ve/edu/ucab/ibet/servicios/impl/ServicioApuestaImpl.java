package ve.edu.ucab.ibet.servicios.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.springframework.security.annotation.Secured;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ve.edu.ucab.ibet.dominio.Apuesta;
import ve.edu.ucab.ibet.dominio.ApuestaPK;
import ve.edu.ucab.ibet.dominio.Evento;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.Politica;
import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
import ve.edu.ucab.ibet.dominio.enums.TipoDocumentoReporte;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.generic.util.mail.interfaces.IMailService;
import ve.edu.ucab.ibet.generic.util.reportes.interfaces.IGenerarReporteFile;
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
    private IGenerarReporteFile generarReporte;
    private IMailService servicioMail;
    private IHelperProperties helperProp;
    private String nombreFactura;
    private Boolean esEmpate = Boolean.FALSE;

    public void esValidaApuestaUsuario(Users usuario, TableroGanancia tablero) {
        if (usuarioHaApostadoEvento(usuario, tablero.getEvento())) {
            throw new ExcepcionNegocio("errors.apuesta.usuario.apuestadublicada");
        } else if (!usuarioPoseeMetodosPago(usuario)) {
            throw new ExcepcionNegocio("errors.apuesta.usuario.nometodospago");
        }
    }

    @Secured({"ROLE_USER"})
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void realizarApuesta(Apuesta apuesta) {
        if (apuesta != null) {
            esValidaApuestaUsuario(apuesta.getUsers(), apuesta.getTableroGanancia());
            esvalidaApuestaCasa(apuesta.getTableroGanancia(), apuesta.getMonto(), apuesta.getMedioPago(), apuesta.getUsers());
            ApuestaPK apuestaPK = new ApuestaPK();
            apuestaPK.setIdMedioPago(apuesta.getMedioPago().getId());
            apuestaPK.setUsername(apuesta.getUsers().getUsername());
            apuesta.setApuestaPK(apuestaPK);
            genericDao.insertar(apuesta);
            generarFactura(apuesta.getUsers(), apuesta);
            enviarSMSApuesta(apuesta.getUsers());
        } else {
            throw new ExcepcionNegocio("errors.apuesta.invalida");
        }
    }

    private void enviarSMSApuesta(Users u) {
        if (u.getUsername().equals("gerardo") || u.getUsername().equals("carlos")) {
            HttpClient cliente = new HttpClient();
            PostMethod post = null;
            post = new PostMethod(helperProp.getString("sms.provedor"));
            post.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
            NameValuePair[] parametersList = new NameValuePair[4];
            parametersList[0] = new NameValuePair("user", helperProp.getString("sms.user"));
            parametersList[1] = new NameValuePair("pass", helperProp.getString("sms.pass"));
            parametersList[2] = new NameValuePair("rcpt", u.getTelefono());
            parametersList[3] = new NameValuePair("text", "Usted ha realizado una apuesta en Ibet. Se ha enviado a su correo la informacion");
            post.setRequestBody(parametersList);
            int httpstatus = 0;
            String response = null;
            try {
                httpstatus = cliente.executeMethod(post);
                response = post.getResponseBodyAsString();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                post.releaseConnection();
                System.out.println(response);
            }
        }
    }

    @Secured({"ROLE_USER"})
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deshacerApuesta(Apuesta apuesta) {
        String username = apuesta.getUsers().getUsername();
        Integer idEvento = apuesta.getTableroGanancia().getEvento().getId();
        StringBuffer query = new StringBuffer("");
        query.append("DELETE FROM APUESTA WHERE username=");
        query.append("'" + username + "'" + "AND idEvento=");
        query.append("'" + idEvento + "'" + "");
        genericDao.ejecturarSQLQueryManipulacion(query.toString());
        File archivo = new File(helperProp.getString("reportes.directorio.correo") + nombreFactura + ".pdf");
        archivo.delete();
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
        if (fechaActual.equals(fechaMaxEvento)) {
            if (horaActualNumber < horaMaxEventoNumber) {
                resultado = Boolean.TRUE;
            }
        }
        if (fechaActual.before(fechaMaxEvento)) {
            resultado = Boolean.TRUE;
        }
        return resultado;
    }

    private Double obtenerCantidadApostadaParaEvento(TableroGanancia tablero) {
        String query = new String();
        Double monto = new Double(0.0);
        Evento evento = servicioEvento.obtenerEventoporTableroGanancia(tablero);
        Object[] parametros = {evento.getId()};
        query = "Select New java.lang.Double (Sum(a.monto)) from Apuesta a where a.tableroGanancia.evento.id =? " +
                "group by a.tableroGanancia.evento.nombre";
        monto = (Double) genericDao.ejecutarQueryUnique(query, parametros);
        if (monto == null) {
            monto = 0.0;

        }
        return monto;
    }

    @SuppressWarnings("unchecked")
    private void generarFactura(Users usuario, Apuesta apuesta) {
        String apostePor;
        Map<String, Object> parameters = new HashMap();
        String titulo = (usuario.getSexo().equalsIgnoreCase("M")) ? "Sr" : "Sra";
        String nombreCompleto = usuario.getNombre() + " " + usuario.getApellido();
        Evento evento = servicioEvento.obtenerEventoporTableroGanancia(apuesta.getTableroGanancia());
        Random random = new Random();
        nombreFactura = usuario.getUsername() + "factura_evento" + evento.getId().toString();
        Integer numeroFactura = java.lang.Math.abs(random.nextInt());
        parameters.put("fecha", UtilMethods.convertirFechaFormato(new java.util.Date()).toString());
        parameters.put("titulo", titulo);
        parameters.put("nombreCompleto", nombreCompleto);
        parameters.put("evento", evento.getNombre());
        apostePor = (esEmpate) ? "Empate" : apuesta.getTableroGanancia().getParticipante().getNombre();
        parameters.put("apostePor", apostePor);
        parameters.put("monto", apuesta.getMonto().toString());
        parameters.put("fechaEvento", UtilMethods.convertirFechaFormato(evento.getFecha()).toString());
        parameters.put("horaEvento", evento.getHora().toString());
        parameters.put("total", apuesta.getMonto().toString());
        parameters.put("numeroFactura", numeroFactura.toString());
        generarReporte.generarReporte(parameters, TipoDocumentoReporte.PDF, nombreFactura);
        enviarCorreoFactura(usuario);
    }

    private void enviarCorreoFactura(Users user) {
        List<String> datosCorreo = new ArrayList<String>();
        String titulo = (user.getSexo().equalsIgnoreCase("M")) ? "Sr" : "Sra";

        datosCorreo.add(titulo);
        datosCorreo.add(user.getNombre() + " " + user.getApellido());
        String asunto = helperProp.getString("correos.factura.plantillas.asunto");
        String cuerpo = ("<p>" + helperProp.getString("correos.factura.plantillas.mensaje.linea1", datosCorreo) + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.factura.plantillas.mensaje.linea2") + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.factura.plantillas.mensaje.linea3") + "</p>");
        File archivo = new File(helperProp.getString("reportes.directorio.correo") + nombreFactura + ".pdf");
        servicioMail.send(user.getCorreo(), asunto, cuerpo, archivo);
    }

    public Apuesta armarApuestaParaRealizar(String idEvento, String idParticipante, String monto, Users usuario) {
        Integer eventoId = null, participanteId = null, montoApuesta = 0;
        Apuesta apuesta = new Apuesta();
        if (UtilMethods.esNumerico(idEvento)) {
            eventoId = Integer.parseInt(idEvento);
        }
        if (UtilMethods.esNumerico(idParticipante)) {
            participanteId = Integer.parseInt(idParticipante);
        }
        if (UtilMethods.esNumerico(monto)) {
            montoApuesta = Integer.parseInt(monto);
        }
        if (participanteId == 0) {
            esEmpate = Boolean.TRUE;
            apuesta.setEmpato(Boolean.TRUE);
            apuesta.setGano(Boolean.FALSE);
        } else if (participanteId != 0) {
            apuesta.setGano(Boolean.TRUE);
            apuesta.setEmpato(Boolean.FALSE);
        }
        Evento eventoApuesta = servicioEvento.obtenerEvento(eventoId);
        if (participanteId == 0) {
            Collection<TableroGanancia> tableros = eventoApuesta.getTableroGananciaCollection();
            for (TableroGanancia tableroGanancia : tableros) {
                participanteId = tableroGanancia.getParticipante().getId();
            }
        }
        TableroGanancia tablero = new TableroGanancia(eventoId, participanteId);
        Participante participante = servicioEvento.obtenerParticipante(participanteId);
        tablero.setParticipante(participante);
        tablero.setEvento(eventoApuesta);
        apuesta.setUsers(usuario);
        apuesta.setTableroGanancia(tablero);
        apuesta.setMonto(new Double(montoApuesta));
        apuesta.setFecha(UtilMethods.convertirFechaFormato(new java.util.Date()));

        return apuesta;
    }

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

    public IGenerarReporteFile getGenerarReporte() {
        return generarReporte;
    }

    public void setGenerarReporte(IGenerarReporteFile generarReporte) {
        this.generarReporte = generarReporte;
    }

    public IMailService getServicioMail() {
        return servicioMail;
    }

    public void setServicioMail(IMailService servicioMail) {
        this.servicioMail = servicioMail;
    }
}
