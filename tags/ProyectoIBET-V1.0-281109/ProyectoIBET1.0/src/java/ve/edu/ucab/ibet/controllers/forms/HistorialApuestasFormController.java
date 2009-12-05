package ve.edu.ucab.ibet.controllers.forms;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import ve.edu.ucab.ibet.dominio.to.reportes.HistorialApuestasTO;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioReportes;

/**
 * Clase para gestionar el controller que muestra en presentacion el historial
 * de apustas realizadas para los usuarios
 * @author Gerardo Barcia
 * @version 1.0
 */
public class HistorialApuestasFormController extends SimpleFormController {

    private IServicioReportes servicioReporte;

    public IServicioReportes getServicioReporte() {
        return servicioReporte;
    }

    public void setServicioReporte(IServicioReportes servicioReporte) {
        this.servicioReporte = servicioReporte;
    }
    

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
       Principal security = request.getUserPrincipal();
       String username = security.getName();
       Date fechaInicio = null;
       Date fechaFin = null;
       List<HistorialApuestasTO> listadoApuestas = servicioReporte.reporteHistorialApuestas(username, fechaInicio, fechaFin);
       ModelAndView mv = new ModelAndView("/privado/front/usuario/historialApuestas");
       mv.addObject("listaHistorial", listadoApuestas);
        return mv;
    }




}
