package ve.edu.ucab.ibet.controllers.views;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ve.edu.ucab.ibet.dominio.Evento;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioEvento;

/**
 * Controlador de presentacion para el index de la administracion
 * @author Gerardo Barcia
 * @version 1.0
 */
public class IndexAdminController implements Controller {

    private IServicioEvento servicioEvento;

    public IServicioEvento getServicioEvento() {
        return servicioEvento;
    }

    public void setServicioEvento(IServicioEvento servicioEvento) {
        this.servicioEvento = servicioEvento;
    }

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Evento> listaEventos = servicioEvento.todosLosEventos();
        return new ModelAndView("privado/back/admin","listaEventos",listaEventos);
    }

}
