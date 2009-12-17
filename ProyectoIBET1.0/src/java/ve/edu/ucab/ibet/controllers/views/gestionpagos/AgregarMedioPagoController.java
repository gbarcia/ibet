/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.controllers.views.gestionpagos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioMedioPago;

/**
 *
 * @author maya
 */
public class AgregarMedioPagoController extends SimpleFormController {

    private IServicioMedioPago servicioMedioPago;

    public AgregarMedioPagoController() {
        setCommandClass(MedioPago.class);
        setCommandName("agregarMedioPago");
    }

    public IServicioMedioPago getServicioMedioPago() {
        return servicioMedioPago;
    }

    public void setServicioMedioPago(IServicioMedioPago servicioMedioPago) {
        this.servicioMedioPago = servicioMedioPago;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        MedioPago medioPago = new MedioPago();
        medioPago.setActivo(Boolean.TRUE);
        return medioPago;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) {
        MedioPago registro = (MedioPago) command;
        ModelAndView mv = new ModelAndView(new RedirectView(getSuccessView()));
        try{
            servicioMedioPago.crearMedioPago(registro);
            mv.addObject("mensaje", "Medio de pago: " + registro.getNombre() + " registrado con exito");
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            mv = new ModelAndView("errorDA");
        } catch (GeneralException ge) {
            ge.printStackTrace();
            errors.rejectValue(null, ge.getKeyError());
            mv = showForm(request, response, errors);
        } finally {
            return mv;
        }
    }

}
