package ve.edu.ucab.ibet.controllers.views.gestionpagos;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioMedioPago;

/**
 * Controlador para desplegar el home de gestion de metodos de pago
 * @author maya
 * @version 1.0
 */
public class HomeMedioPagoController implements Controller {

    private IServicioMedioPago servicioMedioPago;

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<MedioPago> listMedioPagos = servicioMedioPago.listarMedioPagos();
        ModelAndView mv = new ModelAndView("privado/back/pagos/homePagos");
        mv.addObject("pagosList",listMedioPagos);
        return mv;
    }

    public IServicioMedioPago getServicioMedioPago() {
        return servicioMedioPago;
    }

    public void setServicioMedioPago(IServicioMedioPago servicioMedioPago) {
        this.servicioMedioPago = servicioMedioPago;
    }
    
}
