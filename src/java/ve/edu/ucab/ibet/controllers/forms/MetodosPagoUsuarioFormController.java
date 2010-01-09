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
 * Clase para desplegar la gestion de metodos de pago del usuario en la presentacion
 * @author Gerardo Barcia
 * @version 1.0
 */
public class MetodosPagoUsuarioFormController extends MultiActionController {

    private IServicioUsuarioMedioPago servicioUsuarioMedioPago;

    public void setServicioUsuarioMedioPago(IServicioUsuarioMedioPago servicioUsuarioMedioPago) {
        this.servicioUsuarioMedioPago = servicioUsuarioMedioPago;
    }

    public ModelAndView historico(HttpServletRequest req, HttpServletResponse res, Users user){
        List<UsuarioMedioPago> listaHistoria = this.servicioUsuarioMedioPago.mostrarHistorialMedioPago(user);
        return new ModelAndView();
        //TODO: terminar este controlador
    }

}
