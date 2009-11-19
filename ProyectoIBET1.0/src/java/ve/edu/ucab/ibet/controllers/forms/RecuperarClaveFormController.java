package ve.edu.ucab.ibet.controllers.forms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.dominio.to.forms.RecuperarClaveTO;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 * Clase controladora para desplegar el form de recuperacion de clave del usuario
 * @author Gerardo Barcia
 * @version 1.0
 */
public class RecuperarClaveFormController extends SimpleFormController {

    private IServicioUsuario servicioUsuario;

    public IServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(IServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    public RecuperarClaveFormController() {
        setCommandClass(RecuperarClaveTO.class);
        setCommandName("confirmacionClave");
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest req, HttpServletResponse resp, Object command, BindException errors) throws Exception {
        String atributoError = null;
        RecuperarClaveTO registro = (RecuperarClaveTO) command;
        ModelAndView mv = new ModelAndView(new RedirectView(getSuccessView()));
        try {
            servicioUsuario.enviarConfirmacionRecupClave(registro.getCorreo(), registro.getUsername());
            mv.addObject("resultado", "SUCCESS");
        } catch (DataAccessException e) {
            mv = new ModelAndView("errorDA");
            e.printStackTrace();
        } catch (GeneralException e) {
            e.printStackTrace();
            atributoError = servicioUsuario.obtenerAtributoError(e.getKeyError());
            errors.rejectValue(atributoError, e.getKeyError());
            mv = showForm(req, resp, errors);
        } finally {
            return mv;
        }
    }

}
