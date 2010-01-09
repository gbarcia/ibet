package ve.edu.ucab.ibet.controllers.views;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ve.edu.ucab.ibet.dominio.Evento;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioEvento;

/**
 * Controlador de modelo y vista para el home
 * @author jonathan
 */
public class HomeController implements Controller {

    private IServicioEvento servicioEvento;

    public IServicioEvento getServicioEvento() {
        return servicioEvento;
    }

    public void setServicioEvento(IServicioEvento servicioEvento) {
        this.servicioEvento = servicioEvento;
    }

    static Integer PROXIMOS_EVENTOS_MINIMO = 0;
    static Integer PROXIMOS_EVENTOS_MAXIMO = 25;
    static Integer EVENTOS_DESTACADOS_MAXIMO = 5;

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView mv = new ModelAndView("home");
        List<Evento> eventos = servicioEvento.obtenerProximosEventos(PROXIMOS_EVENTOS_MINIMO, PROXIMOS_EVENTOS_MAXIMO);
        List<Evento> eventosImagenes = servicioEvento.obtenerProximosEventosConImagen(PROXIMOS_EVENTOS_MINIMO ,EVENTOS_DESTACADOS_MAXIMO);
        mv.addObject("eventos", eventos);
        mv.addObject("eventosImagenes", eventosImagenes);
        return mv;
    }
}
