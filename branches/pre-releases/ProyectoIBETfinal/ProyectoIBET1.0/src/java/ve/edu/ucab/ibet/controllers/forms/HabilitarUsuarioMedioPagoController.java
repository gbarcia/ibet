/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.controllers.forms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.servlet.mvc.SimpleFormController;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioMedioPago;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuarioMedioPago;
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

/**
 *
 * @author jonathan
 */
public class HabilitarUsuarioMedioPagoController extends SimpleFormController {

    private IServicioMedioPago servicioMedioPago;

    private IServicioUsuarioMedioPago servicioUsuarioMedioPago;

    public IServicioMedioPago getServicioMedioPago() {
        return servicioMedioPago;
    }

    public void setServicioMedioPago(IServicioMedioPago servicioMedioPago) {
        this.servicioMedioPago = servicioMedioPago;
    }

    public IServicioUsuarioMedioPago getServicioUsuarioMedioPago() {
        return servicioUsuarioMedioPago;
    }

    public void setServicioUsuarioMedioPago(IServicioUsuarioMedioPago servicioUsuarioMedioPago) {
        this.servicioUsuarioMedioPago = servicioUsuarioMedioPago;
    }

    public HabilitarUsuarioMedioPagoController() {
        setCommandClass(UsuarioMedioPago.class);
        setCommandName("usuarioMedioPago");
    }

   @Override
    protected ModelAndView onSubmit(HttpServletRequest req, HttpServletResponse resp, Object command, BindException errors) {
        String atributoError = null;
        UsuarioMedioPago registro = (UsuarioMedioPago) command;
        ModelAndView mv = new ModelAndView(new RedirectView(getSuccessView()));
        try {
            this.servicioUsuarioMedioPago.ActivarMedioPago(registro.getMedioPago(),
                    registro.getMontoMaximo(), registro.getUsers());
            mv.addObject("resultado", "SUCCESS");
        } catch (DataAccessException e) {
            mv = new ModelAndView("errorDA");
            e.printStackTrace();
        } finally {
            return mv;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request) {
        Map referenceData = new HashMap();
        referenceData.put("listaMetodosPago", this.servicioMedioPago.listarMedioPagos());
        return referenceData;
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
