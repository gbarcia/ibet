package ve.edu.ucab.ibet.controllers.reportes.interfaces;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;

/**
 * Interfaz para la generacion de reportes por pantalla
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IReporteGenerator {

    public ModelAndView generarReportePDF(HttpServletRequest request,
            HttpServletResponse response) throws GeneralException;

    public ModelAndView generarReporteXLS(HttpServletRequest request,
            HttpServletResponse response) throws GeneralException;

    public Map getModel() throws GeneralException;

    public List getData() throws GeneralException;
}
