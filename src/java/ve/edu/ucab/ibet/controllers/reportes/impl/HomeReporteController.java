package ve.edu.ucab.ibet.controllers.reportes.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ve.edu.ucab.ibet.dominio.to.reportes.DetalleReporteTO;

/**
 * Clase para presentar los reportes y sus vinculos
 * @author Gerardo Barcia
 * @version 1.0
 */
public class HomeReporteController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        List<DetalleReporteTO> listaReportes = new ArrayList<DetalleReporteTO>();
        DetalleReporteTO reporteGanancias = new DetalleReporteTO("Reporte de Ganancias " +
                "Ibet", "Gerencial","reporteGanancias.htm");
        DetalleReporteTO reportePerdidas = new DetalleReporteTO("Reporte de Perdidas Ibet",
                "Gerencial","reportePerdidas.htm");
        DetalleReporteTO eventosAltoR = new DetalleReporteTO("Reporte de Eventos de Alto Riesgo",
                "Gerencial","reporteEventosAltoRiesgo.htm");
        DetalleReporteTO eventosUsuarioCat = new DetalleReporteTO("Reporte de Usuarios por" +
                " Categoria", "Administrativo", "reporteCantidadUsuariosPorCategoria.htm");
        DetalleReporteTO eventosUsuarioAc = new DetalleReporteTO("Reporte de Usuarios con " +
                "mayor aciertos", "Administrativo", "reporteUsuariosMayorAciertos.htm");
        listaReportes.add(reporteGanancias);
        listaReportes.add(reportePerdidas);
        listaReportes.add(eventosAltoR);
        listaReportes.add(eventosUsuarioAc);
        listaReportes.add(eventosUsuarioCat);
        return new ModelAndView("privado/back/reportes/homeReportes","listaReportes",listaReportes);
    }
}
