package ve.edu.ucab.ibet.controllers.forms;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.to.forms.RegistroUsuarioTO;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.generic.util.DatosUtil;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 * Controlador web para la vista del registro de usuario
 * @author Gerardo Barcia
 * @version 1.0
 */
public class RegistroUsuarioFormController extends SimpleFormController {

    private IServicioUsuario servicioUsuario;

    public RegistroUsuarioFormController() {
        setCommandClass(RegistroUsuarioTO.class);
        setCommandName("registroUsuario");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        RegistroUsuarioTO registro = new RegistroUsuarioTO();
        return registro;
    }

    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map referenceData = new HashMap();
        referenceData.put("opcionSexo", DatosUtil.SEXO);
        referenceData.put("opcionEstado", DatosUtil.ESTADOS);
        referenceData.put("opcionPais", DatosUtil.PAIS);
        return referenceData;
    }

    @Override
    protected ModelAndView onSubmit(Object command, BindException errors) throws Exception {
        RegistroUsuarioTO registro = (RegistroUsuarioTO) command;
        Users usuario = servicioUsuario.transferObjectToModel(registro);
        ModelAndView mv = new ModelAndView(new RedirectView(getSuccessView()));
        try {
            servicioUsuario.registroNuevoUsuarioM(usuario);
            mv.addObject("resultado", "SUCCESS");
        } catch (DataAccessException e) {
            mv = new ModelAndView("errorDA");
            e.printStackTrace();
        } catch (GeneralException e) {
            e.printStackTrace();
            mv.addObject("errorNegocio", e.getKeyError());
        } finally {
            return mv;
        }
    }

    public IServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(IServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }
}
