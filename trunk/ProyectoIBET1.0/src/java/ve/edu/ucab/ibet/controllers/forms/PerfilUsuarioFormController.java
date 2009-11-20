/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.ibet.controllers.forms;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.to.forms.RegistroUsuarioTO;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.generic.util.DatosUtil;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 *
 * @author maya
 */
public class PerfilUsuarioFormController extends SimpleFormController {

    private IServicioUsuario servicioUsuario;

    public PerfilUsuarioFormController() {
        setCommandClass(RegistroUsuarioTO.class);
        setCommandName("perfil");
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest req, HttpServletResponse resp, Object command, BindException errors) {
        String atributoError = null;
        RegistroUsuarioTO registro = (RegistroUsuarioTO) command;
        Users usuario = servicioUsuario.transferObjectToModel(registro);
        ModelAndView mv = new ModelAndView(new RedirectView(getSuccessView()));
        try {
            servicioUsuario.actualizarDatosUsuarioM(usuario);
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

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request) {
        Map referenceData = new HashMap();
        referenceData.put("opcionSexo", DatosUtil.SEXO);
        referenceData.put("opcionEstado", DatosUtil.ESTADOS);
        referenceData.put("opcionPais", DatosUtil.PAIS);
        return referenceData;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        Principal security = request.getUserPrincipal();
        Users user = servicioUsuario.obtenerDatosUsuarioM(security.getName());
        return servicioUsuario.modelToTransferObject(user);
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    public IServicioUsuario getServicioUsuario() {
        return servicioUsuario;
    }

    public void setServicioUsuario(IServicioUsuario servicioPerfil) {
        this.servicioUsuario = servicioPerfil;
    }
}
