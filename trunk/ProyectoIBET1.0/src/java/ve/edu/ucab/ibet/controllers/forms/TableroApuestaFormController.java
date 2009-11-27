package ve.edu.ucab.ibet.controllers.forms;


import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.dominio.Apuesta;
import ve.edu.ucab.ibet.dominio.Evento;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
import ve.edu.ucab.ibet.dominio.to.forms.RegistroApuestaTO;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioApuesta;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioEvento;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioMedioPago;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 * Controlador de formulario para desplegar y obtener los datos de la apuesta
 * a ser realizada
 * @author Gerardo Barcia
 * @version 1.0
 */
public class TableroApuestaFormController extends SimpleFormController {

    private IServicioApuesta servicioApuesta;
    private IServicioUsuario servicioUsuario;
    private IServicioMedioPago servicioMedioPago;
    private IServicioEvento servicioEvento;
    private Apuesta apuesta;
    private Principal security;
    private Users usuario;


    public IServicioApuesta getServicioApuesta() {
        return servicioApuesta;
    }

    public void setServicioApuesta(IServicioApuesta servicioApuesta) {
        this.servicioApuesta = servicioApuesta;
    }

    public IServicioMedioPago getServicioMedioPago() {
        return servicioMedioPago;
    }

    public void setServicioMedioPago(IServicioMedioPago servicioMedioPago) {
        this.servicioMedioPago = servicioMedioPago;
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

    public TableroApuestaFormController() {
        setCommandClass(RegistroApuestaTO.class);
        setCommandName("apuesta");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        Integer eventoId = null,participanteId = null,monto = 0;
        String idEvento = request.getParameter("ide");
        String idParticipante = request.getParameter("idp");
        String montoApuesta = request.getParameter("m");
        if (UtilMethods.esNumerico(idEvento)) eventoId = Integer.parseInt(idEvento);
        if (UtilMethods.esNumerico(idParticipante)) participanteId = Integer.parseInt(idParticipante);
        if (UtilMethods.esNumerico(montoApuesta)) monto = Integer.parseInt(montoApuesta);
        security = request.getUserPrincipal();
        String nombreUsuario = security.getName();
        usuario = servicioUsuario.obtenerDatosUsuarioM(nombreUsuario);
        TableroGanancia tablero = new TableroGanancia(eventoId, participanteId);
        Evento eventoApuesta = servicioEvento.obtenerEvento(eventoId);
        Participante participante = servicioEvento.obtenerParticipante(participanteId);
        tablero.setParticipante(participante);
        tablero.setEvento(eventoApuesta);
        apuesta = new Apuesta();
        apuesta.setUsers(usuario);
        apuesta.setTableroGanancia(tablero);
        apuesta.setFecha(UtilMethods.convertirFechaFormato(new java.util.Date()));
        if (participanteId == 0) {
            apuesta.setEmpato(Boolean.TRUE);
            apuesta.setGano(Boolean.FALSE);
        }
        else {
            apuesta.setGano(Boolean.TRUE);
            apuesta.setEmpato(Boolean.FALSE);
        }
        RegistroApuestaTO comando = new RegistroApuestaTO();
        comando.setFechaEvento(eventoApuesta.getFecha());
        comando.setMontoApuesta(new Double(monto));
        comando.setNombreEvento(eventoApuesta.getNombre());
        return comando;
    }

     @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map referenceData = new HashMap();
        List<UsuarioMedioPago> listaMetodosPagoVigentes = servicioUsuario.obtenerMediosPagoVigenteUsuario(usuario);
        List<String> listaNombresMediosPago = servicioMedioPago.obtenerNombresMediosPagoVigentes(listaMetodosPagoVigentes);
        referenceData.put("opcionMetodosPago",listaNombresMediosPago);
        return referenceData;
    }

     @Override
    protected ModelAndView onSubmit(HttpServletRequest req, HttpServletResponse resp, Object command, BindException errors) throws Exception {
        String atributoError = null;
        Boolean resultado = Boolean.FALSE;
        String mensaje = "";
        String idEvento = apuesta.getTableroGanancia().getEvento().getId().toString();
        String idParticipante = apuesta.getTableroGanancia().getParticipante().getId().toString();
        RegistroApuestaTO registro = (RegistroApuestaTO) command;
        apuesta.setMonto(registro.getMontoApuesta());
        MedioPago medioPago = servicioMedioPago.obtenerMedioPago(registro.getNombreMetodoPago());
        apuesta.setMedioPago(medioPago);
        String monto = apuesta.getMonto().toString();
        ModelAndView mv = new ModelAndView(new RedirectView(req.getContextPath() +
                "/privado/front/apuesta/tableroApuesta.htm?" + "ide=" + idEvento +
                "&idp=" + idParticipante + "&m=" + monto));
        try {
            servicioApuesta.realizarApuesta(apuesta);
            resultado = Boolean.TRUE;
            mensaje = "exito.registro.usuario";
        } catch (DataAccessException e) {
            mensaje = "error.database.notfound";
            e.printStackTrace();
        } catch (GeneralException e) {
            e.printStackTrace();
            atributoError = servicioUsuario.obtenerAtributoError(e.getKeyError());
            errors.rejectValue(atributoError, e.getKeyError());
            mv = showForm(req, resp, errors);
        } finally {
            if (!mensaje.equals("")) {
                mv.addObject("resultado", resultado);
                mv.addObject("mensaje", mensaje);
            }
            return mv;
        }
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
