package ve.edu.ucab.ibet.controllers.reportes.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import ve.edu.ucab.ibet.controllers.reportes.interfaces.IReporteGenerator;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioReportes;

/**
 *Clase controladora para generar los reportes de tipo Eventos con mayor y menor riesgo
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ReporteEventosRiesgo extends MultiActionController implements IReporteGenerator {

    private final String NOMBRE_REP_PDF = "repEventosRiesgoPDF";
    private final String NOMBRE_REP_XLS = "repEventosRiesgoXLS";
    private IServicioReportes servicioReportes;

    public ModelAndView generarReportePDF(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        Double monto = Double.valueOf(request.getParameter("monto"));
        return new ModelAndView(NOMBRE_REP_PDF, getModel(monto));
    }

    public ModelAndView generarReporteXLS(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        Double monto = Double.valueOf(request.getParameter("monto"));
        return new ModelAndView(NOMBRE_REP_XLS, getModel(monto));
    }

    @SuppressWarnings("unchecked")
    public Map getModel(Double monto) throws GeneralException {
        Map model = new HashMap();
        model.put("monto", monto);
        model.put("dataSource", getData(monto));
        return model;
    }

    @SuppressWarnings("unchecked")
    public List getData(Double monto) throws GeneralException {
        List lista = new ArrayList();
        lista.addAll(servicioReportes.reporteEventosAltoRiesgo(monto));
        return lista;
    }

    public IServicioReportes getServicioReportes() {
        return servicioReportes;
    }

    public void setServicioReportes(IServicioReportes servicioReportes) {
        this.servicioReportes = servicioReportes;
    }
    
}
