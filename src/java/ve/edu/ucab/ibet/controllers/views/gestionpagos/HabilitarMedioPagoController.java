package ve.edu.ucab.ibet.controllers.views.gestionpagos;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioMedioPago;

/**
 * Controlador para habilitar un metodo de pago en el sistema
 * @author maya
 * @version 1.0
 */
public class HabilitarMedioPagoController implements Controller {

    IServicioMedioPago servicioMedioPago;

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String idMedioPagoStr = req.getParameter("id");
        Integer idMedioPago = Integer.parseInt(idMedioPagoStr);
        MedioPago medioPago = servicioMedioPago.obtenerMedioPagoId(idMedioPago);
        servicioMedioPago.habilitarMedioPago(medioPago);
        List<MedioPago> listaMedioPagos = servicioMedioPago.listarMedioPagos();
        ModelAndView mv = new ModelAndView(new RedirectView("/ProyectoIBET/privado/back/pagos/homeMedioPago.htm"));
        mv.addObject("mensaje", "Medio de pago habilitado con exito");
        mv.addObject("pagosList", listaMedioPagos);
        return mv;
    }

    public IServicioMedioPago getServicioMedioPago() {
        return servicioMedioPago;
    }

    public void setServicioMedioPago(IServicioMedioPago servicioMedioPago) {
        this.servicioMedioPago = servicioMedioPago;
    }

}
