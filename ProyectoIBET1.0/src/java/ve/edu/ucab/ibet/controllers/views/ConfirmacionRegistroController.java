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
 * Clase de presentacion para la confirmacion del registros
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ConfirmacionRegistroController implements Controller {

    private IServicioUsuario servicioUsuario;

    public IServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(IServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String decodeUserName = req.getParameter("user");
        String username = UtilMethods.decrypt(decodeUserName);
        System.out.println(username);
        String mensaje = "";
        Boolean resultado = Boolean.FALSE;
        ModelAndView mv = new ModelAndView("publico/registroConfirmado");
        try {
            servicioUsuario.confirmarRegistroUsuario(username);
            mensaje = "exito.registro.confirmado";
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
