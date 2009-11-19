package ve.edu.ucab.ibet.controllers.views;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 * Clase para el controller de recuperacion de clave que muestra la presentacion
 * para recuperar la clave de un usuario afectado
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ConfirmacionRecuperacionClaveController implements Controller {

    private IServicioUsuario servicioUsuario;

    public IServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(IServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        String decode = req.getParameter("user");
        String username= UtilMethods.decrypt(decode);
        String mensaje = "";
        Boolean resultado = Boolean.FALSE;
        ModelAndView mv = new ModelAndView("publico/recuperacionClave");
        try {
            servicioUsuario.recuperarClave(username);
            mensaje = "exito.nuevaclave.confirmado";
            resultado = Boolean.TRUE;
        } catch (DataAccessException dae) {
            mensaje = "error.database.notfound";
            dae.printStackTrace();
        } catch (GeneralException ge) {
            ge.printStackTrace();
            mensaje = "error.usuario.invalido";
        }
        mv.addObject("resultado", resultado);
        mv.addObject("mensaje", mensaje);
        return mv;
    }
}
