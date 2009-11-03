/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.controllers.forms;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioPerfilUsuario;

/**
 *
 * @author maya
 */
public class PerfilUsuarioFormController extends SimpleFormController{

    private IServicioPerfilUsuario servicioPerfil;
    private static final String[] SEXO = { "F", "M" };

    public PerfilUsuarioFormController() {
        setCommandClass(Users.class);
        setCommandName("perfilUsuario");
    }

    @Override
    protected ModelAndView onSubmit(Object command, BindException errors) throws Exception {
        return super.onSubmit(command, errors);
    }

    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
        Map referenceData = new HashMap();
        referenceData.put("sexor", SEXO);
        return referenceData;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(true);
        Users user = servicioPerfil.obtenerDatosUsuarioM("maya");

        return user;
    }



    public IServicioPerfilUsuario getServicioPerfil() {
        return servicioPerfil;
    }

    public void setServicioPerfil(IServicioPerfilUsuario servicioPerfil) {
        this.servicioPerfil = servicioPerfil;
    }

    

}
