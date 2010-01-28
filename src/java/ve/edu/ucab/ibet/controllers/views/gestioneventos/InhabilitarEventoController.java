package ve.edu.ucab.ibet.controllers.views.gestioneventos;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.dominio.Evento;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioEvento;

/**
 * Clase de controlo para inhabilitar un evento
 * @author Gerardo Barcia
 * @version 1.0
 */
public class InhabilitarEventoController implements Controller {

    private IServicioEvento servicioEvento;

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String idEventoStr = req.getParameter("id");
        Integer idEvento = Integer.parseInt(idEventoStr);
        servicioEvento.desactivarEvento(idEvento);
        List<Evento> listaEventos = servicioEvento.todosLosEventos();
        ModelAndView mv = new ModelAndView(new RedirectView("/ProyectoIBET/privado/back/admin.htm"));
        mv.addObject("mensaje", "Evento inhabilitado con exito");
        mv.addObject("listaEventos", listaEventos);
        return mv;
    }

    public IServicioEvento getServicioEvento() {
        return servicioEvento;
    }

    public void setServicioEvento(IServicioEvento servicioEvento) {
        this.servicioEvento = servicioEvento;
    }
}
