package ve.edu.ucab.ibet.controllers.forms;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuarioMedioPago;

/**
 *
 * @author jonathan
 */
public class UsuarioMedioPagoController extends MultiActionController {

    private IServicioUsuarioMedioPago servicioUsuarioMedioPago;

    public void setServicioUsuarioMedioPago(IServicioUsuarioMedioPago servicioUsuarioMedioPago) {
        this.servicioUsuarioMedioPago = servicioUsuarioMedioPago;
    }

    public ModelAndView historial(HttpServletRequest req,
            HttpServletResponse res) throws Exception {
        String username = req.getParameter("username");
        Users usuario = new Users(username);
        List<UsuarioMedioPago> historial = this.servicioUsuarioMedioPago.mostrarHistorialMedioPago(usuario);
        ModelAndView mv = new ModelAndView();
        mv.addObject("historial", historial);
        return mv;
    }

//    public ModelAndView actualizarMontoMaximo(HttpServletRequest req,
//            HttpServletResponse res,
//            UsuarioMedioPago usuarioMedioPago) throws Exception {
//        servicioUsuarioMedioPago.ActualizarMontoMaximo(usuarioMedioPago.getMontoMaximo(),
//                usuarioMedioPago.getMedioPago(),
//                usuarioMedioPago.getUsers());
//        return new ModelAndView("redirect:historial.htm");
//    }
}
