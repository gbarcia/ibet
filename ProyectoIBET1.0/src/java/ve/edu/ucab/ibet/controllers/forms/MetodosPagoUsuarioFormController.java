package ve.edu.ucab.ibet.controllers.forms;

import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuarioMedioPago;

/**
 * Clase para desplegar la gestion de metodos de pago del usuario en la presentacion
 * @author Gerardo Barcia
 * @version 1.0
 */
public class MetodosPagoUsuarioFormController implements Controller {

    private IServicioUsuarioMedioPago servicioUsuarioMedioPago;

    public void setServicioUsuarioMedioPago(IServicioUsuarioMedioPago servicioUsuarioMedioPago) {
        this.servicioUsuarioMedioPago = servicioUsuarioMedioPago;
    }

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse res) throws Exception {
        Principal security = req.getUserPrincipal();
        String username = security.getName();
        List<UsuarioMedioPago> historial = this.servicioUsuarioMedioPago.mostrarHistorialMedioPago(new Users(username));
        ModelAndView mv = new ModelAndView();
        mv.addObject("historial", historial);
        return mv;
    }
}
