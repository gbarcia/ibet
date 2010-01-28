package ve.edu.ucab.ibet.controllers.views.gestioneventos;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.dominio.Evento;
import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.dominio.to.forms.FinalizarEventoTO;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioEvento;
import winterwell.jtwitter.TwitterException;

/**
 * Clase de controlador para finalizar un evento en el sistema
 * @author Gerardo Barcia
 * @version 1.0
 */
public class FinalizarEventoFormController  extends SimpleFormController{

    private IServicioEvento servicioEvento;

    public FinalizarEventoFormController() {
        setCommandClass(FinalizarEventoTO.class);
        setCommandName("finalizarEvento");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        FinalizarEventoTO registro = new FinalizarEventoTO();
        registro.setIdParticipanteGanador(null);
        registro.setPartidaEmpatada(new Boolean(Boolean.TRUE));
        registro.setResultado(new String());
        return registro;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request) {
        String eventoStr = request.getParameter("id");
        Integer idEvento = Integer.parseInt(eventoStr);
        Evento evento = servicioEvento.obtenerEvento(idEvento);
        Map<Integer,String> participantes = new HashMap<Integer, String>();
        for (TableroGanancia t : evento.getTableroGananciaCollection()) {
            participantes.put(t.getParticipante().getId(), t.getParticipante().getNombre());
        }
        Map<Boolean,String> opcionBooleana = new HashMap<Boolean, String>();
        opcionBooleana.put(Boolean.TRUE, "SI");
        opcionBooleana.put(Boolean.FALSE, "NO");
        Map referenceData = new HashMap();
        referenceData.put("opcionBooleana", opcionBooleana);
        referenceData.put("participantes", participantes);
        return referenceData;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest req, HttpServletResponse resp,
            Object command, BindException errors) {
        String eventoStr = req.getParameter("id");
        Integer idEvento = Integer.parseInt(eventoStr);
        String atributoError = null;
        Evento evento = servicioEvento.obtenerEvento(idEvento);
        FinalizarEventoTO registro = (FinalizarEventoTO) command;
        Integer idParticipante = registro.getPartidaEmpatada() ? null : registro.getIdParticipanteGanador();
        ModelAndView mv = new ModelAndView(new RedirectView(getSuccessView()));
        try {
            Boolean ganar = (!registro.getPartidaEmpatada()) ? Boolean.TRUE : Boolean.FALSE;
            Boolean empate = registro.getPartidaEmpatada();
            servicioEvento.finalizarEvento(idEvento,registro.getResultado(), idParticipante,ganar , empate);
            mv.addObject("mensaje", "Evento "+ evento.getNombre() + " finalizado con exito");
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            mv = new ModelAndView("errorDA");
        } catch (GeneralException ge) {
            ge.printStackTrace();
            errors.rejectValue("resultado", ge.getKeyError());
            mv = showForm(req, resp, errors);
        } catch (TwitterException te) {
            te.printStackTrace();
            errors.rejectValue(null, "No se pudo enviar el mensaje a twitter");
            mv = showForm(req, resp, errors);
        }finally {
            return mv;
        }
    }

    public IServicioEvento getServicioEvento() {
        return servicioEvento;
    }

    public void setServicioEvento(IServicioEvento servicioEvento) {
        this.servicioEvento = servicioEvento;
    }
    
}
