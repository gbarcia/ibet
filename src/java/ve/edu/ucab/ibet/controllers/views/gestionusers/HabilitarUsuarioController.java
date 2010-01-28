package ve.edu.ucab.ibet.controllers.views.gestionusers;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 * Controlador para habilitar un usuario en el sistema
 * @author maya
 * @version 1.0
 */
public class HabilitarUsuarioController implements Controller {

    private IServicioUsuario servicioUsuario;

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("username");
        servicioUsuario.habilitarUsuario(username);
        List<Users> listaUsuarios = servicioUsuario.listarUsuarios();
        ModelAndView mv = new ModelAndView(new RedirectView("/ProyectoIBET/privado/back/usuario/homeUsuarios.htm"));
        mv.addObject("mensaje", "Usuario habilitado con exito");
        mv.addObject("usuariosList", listaUsuarios);
        return mv;
    }

    public IServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(IServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

}
