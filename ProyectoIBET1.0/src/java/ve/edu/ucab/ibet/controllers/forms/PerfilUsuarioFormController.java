/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.controllers.forms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.generic.util.DatosUtil;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 *
 * @author maya
 */
public class PerfilUsuarioFormController extends SimpleFormController{

    private IServicioUsuario servicioPerfil;

    public PerfilUsuarioFormController() {
        setCommandClass(Users.class);
        setCommandName("perfil");
    }

    @Override
    protected ModelAndView onSubmit(Object command, BindException errors) {
        Users u = (Users) command;
        try {
            servicioPerfil.actualizarDatosUsuarioM(u);
        } catch (GeneralException ex) {
            Logger.getLogger(PerfilUsuarioFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request)  {
        Map referenceData = new HashMap();
        referenceData.put("sexor", DatosUtil.SEXO);
        return referenceData;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        Users user = servicioPerfil.obtenerDatosUsuarioM("x");
        return user;
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
//        binder.registerCustomEditor(Date.class, "projectInfo.beginDate",
//        new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true));
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }





    public IServicioUsuario getServicioPerfil() {
        return servicioPerfil;
    }

    public void setServicioPerfil(IServicioUsuario servicioPerfil) {
        this.servicioPerfil = servicioPerfil;
    }

    

}
