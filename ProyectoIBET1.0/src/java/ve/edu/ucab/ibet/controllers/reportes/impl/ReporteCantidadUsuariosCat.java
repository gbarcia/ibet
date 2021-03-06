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
 * Clase controladora para generar los reportes de tipo Cantidad de usuarios por categoria
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ReporteCantidadUsuariosCat extends MultiActionController implements IReporteGenerator {

    private final String NOMBRE_REP_PDF = "repCantidadUsuariosPorCatPDF";
    private final String NOMBRE_REP_XLS = "repCantidadUsuariosPorCatXLS";
    private IServicioReportes servicioReportes;

    public ModelAndView generarReportePDF(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        return new ModelAndView(NOMBRE_REP_PDF, getModel());
    }

    public ModelAndView generarReporteXLS(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        return new ModelAndView(NOMBRE_REP_XLS, getModel());
    }

    @SuppressWarnings("unchecked")
    public Map getModel() throws GeneralException {
        Map model = new HashMap();
        model.put("dataSource", getData());
        return model;
    }

    @SuppressWarnings("unchecked")
    public List getData() throws GeneralException {
        List lista = new ArrayList();
        lista.addAll(servicioReportes.reporteCantidadUsuariosCategoria());
        return lista;
    }

    public IServicioReportes getServicioReportes() {
        return servicioReportes;
    }

    public void setServicioReportes(IServicioReportes servicioReportes) {
        this.servicioReportes = servicioReportes;
    }
}
