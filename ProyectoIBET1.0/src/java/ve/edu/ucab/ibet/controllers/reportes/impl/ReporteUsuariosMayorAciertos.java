package ve.edu.ucab.ibet.controllers.reportes.impl;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import ve.edu.ucab.ibet.controllers.reportes.interfaces.IReporteGenerator;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;

/**
 * Clase controladora para generar los reportes de tipo Usuarios Mayor Aciertos
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ReporteUsuariosMayorAciertos extends MultiActionController implements IReporteGenerator {

    private final String NOMBRE_REP_PDF = "repUsuariosMayorAciertosPDF";
    private final String NOMBRE_REP_XLS = "repUsuariosMayorAciertosXLS";

    public ModelAndView generarReportePDF(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        return new ModelAndView(NOMBRE_REP_PDF, getModel());
    }

    public ModelAndView generarReporteXLS(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        return new ModelAndView(NOMBRE_REP_XLS, getModel());
    }

    public Map getModel() throws GeneralException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List getData() throws GeneralException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
