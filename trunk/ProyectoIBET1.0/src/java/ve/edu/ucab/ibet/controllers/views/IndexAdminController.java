package ve.edu.ucab.ibet.controllers.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Controlador de presentacion para el index de la administracion
 * @author Gerardo Barcia
 * @version 1.0
 */
public class IndexAdminController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return new ModelAndView("privado/back/admin");
    }

}
