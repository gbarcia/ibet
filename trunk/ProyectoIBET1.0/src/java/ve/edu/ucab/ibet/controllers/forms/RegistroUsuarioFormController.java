package ve.edu.ucab.ibet.controllers.forms;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.SimpleFormController;
import ve.edu.ucab.ibet.dominio.to.forms.RegistroUsuarioTO;
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
    protected Map referenceData(HttpServletRequest request) throws Exception{
        Map referenceData = new HashMap();
        referenceData.put("opcionSexo", DatosUtil.SEXO);
        return referenceData;
    }

    public IServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(IServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }
}