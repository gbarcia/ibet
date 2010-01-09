package ve.edu.ucab.ibet.controllers.reportes.interfaces;

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

    /**
     * metodo para generar reporte en formato pdf
     * @param request objeto request
     * @param response objeto response
     * @return ModelAndView con el reporte en formato PDF
     * @throws GeneralException excepciones de negocio o bd
     */
    public ModelAndView generarReportePDF(HttpServletRequest request,
            HttpServletResponse response) throws GeneralException;

    /**
     * metodo para generar reporte en formato xls
     * @param request objeto request
     * @param response objeto response
     * @return ModelAndView con rel porte en formato XLS
     * @throws GeneralException excepciones de negocio o bd
     */
    public ModelAndView generarReporteXLS(HttpServletRequest request,
            HttpServletResponse response) throws GeneralException;
}
