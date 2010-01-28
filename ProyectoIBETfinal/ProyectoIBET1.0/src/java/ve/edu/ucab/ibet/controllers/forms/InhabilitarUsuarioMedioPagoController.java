/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.controllers.forms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuarioMedioPago;

/**
 *
 * @author jonathan
 */
public class InhabilitarUsuarioMedioPagoController implements Controller {

    private IServicioUsuarioMedioPago servicioUsuarioPago;

    public IServicioUsuarioMedioPago getServicioUsuarioPago() {
        return servicioUsuarioPago;
    }

    public void setServicioUsuarioPago(IServicioUsuarioMedioPago servicioUsuarioPago) {
        this.servicioUsuarioPago = servicioUsuarioPago;
    }

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String usuario = req.getParameter("username");
        Integer idMedioPago = Integer.parseInt(req.getParameter("idMedioPago"));
        this.servicioUsuarioPago.DesactivarMedioPago(new MedioPago(idMedioPago), new Users(usuario));
        ModelAndView mv = new ModelAndView(new RedirectView("/ProyectoIBET/privado/front/usuario/metodosPagoUsuario.htm"));
        mv.addObject("mensaje","Medio de pago inhabilitado con exito.");
        return mv;
    }

}
