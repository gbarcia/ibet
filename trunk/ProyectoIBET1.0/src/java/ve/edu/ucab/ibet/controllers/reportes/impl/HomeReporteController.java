package ve.edu.ucab.ibet.controllers.reportes.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * Clase para presentar los reportes y sus vinculos
 * @author Gerardo Barcia
 * @version 1.0
 */
public class HomeReporteController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        return new ModelAndView("privado/back/reportes/homeReportes");
    }

}
