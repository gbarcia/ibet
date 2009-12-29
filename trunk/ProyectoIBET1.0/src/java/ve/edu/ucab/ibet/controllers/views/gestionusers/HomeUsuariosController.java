/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.controllers.views.gestionusers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 *
 * @author maya
 */
public class HomeUsuariosController implements Controller {

    private IServicioUsuario servicioUsuario;

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Users> listUsuarios = servicioUsuario.listarUsuarios();
        ModelAndView mv = new ModelAndView("privado/back/usuario/homeUsuarios");
        mv.addObject("usuariosList",listUsuarios);
        return mv;
    }

    public IServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(IServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }
}
