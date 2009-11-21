package ve.edu.ucab.ibet.controllers.reportes.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import ve.edu.ucab.ibet.controllers.reportes.interfaces.IReporteGenerator;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioReportes;

/**
 * Clase controladora para generar los reportes de tipo historial de apuestas
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ReporteHistorialUsuarioController extends MultiActionController implements IReporteGenerator{

    private final String NOMBRE_REP_PDF = "repHistorialApuestasPDF";
    private final String NOMBRE_REP_XLS = "repHistorialApuestasXLS";
    private IServicioReportes servicioReportes;

    public ModelAndView generarReportePDF(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        Date fechaInicio = UtilMethods.stringToFecha(request.getParameter("fechaInicio"));
        Date fechaFin = UtilMethods.stringToFecha(request.getParameter("fechaFin"));
        return new ModelAndView(NOMBRE_REP_PDF, getModel(request.getParameter("username"), fechaInicio, fechaFin));
    }


    public ModelAndView generarReporteXLS(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        Date fechaInicio = UtilMethods.stringToFecha(request.getParameter("fechaInicio"));
        Date fechaFin = UtilMethods.stringToFecha(request.getParameter("fechaFin"));
        return new ModelAndView(NOMBRE_REP_XLS, getModel(request.getParameter("username"), fechaInicio, fechaFin));
    }

    @SuppressWarnings("unchecked")
    public Map getModel(String username, Date fechaInicio, Date fechaFin) throws GeneralException {
        Map model = new HashMap();
        model.put("username", username);
        model.put("fechaInicio", fechaInicio);
        model.put("fechaFin", fechaFin);
        model.put("dataSource", getData(username, fechaInicio, fechaFin));
        return model;
    }

    @SuppressWarnings("unchecked")
    public List getData(String username, Date fechaInicio, Date fechaFin) throws GeneralException {
        List lista = new ArrayList();
        lista.addAll(servicioReportes.reporteHistorialApuestas(username, fechaInicio, fechaFin));
        return lista;
    }

    public IServicioReportes getServicioReportes() {
        return servicioReportes;
    }

    public void setServicioReportes(IServicioReportes servicioReportes) {
        this.servicioReportes = servicioReportes;
    }
}
