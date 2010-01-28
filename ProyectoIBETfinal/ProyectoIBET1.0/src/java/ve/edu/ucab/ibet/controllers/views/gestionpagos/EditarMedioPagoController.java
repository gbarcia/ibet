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
 * Controlador para editar un medio de pago en el sistema
 * @author maya
 * @version 1.0
 */
public class EditarMedioPagoController extends SimpleFormController {

    private IServicioMedioPago servicioMedioPago;

    public EditarMedioPagoController() {
        setCommandClass(MedioPago.class);
        setCommandName("editarMedioPago");
    }

    public IServicioMedioPago getServicioMedioPago() {
        return servicioMedioPago;
    }

    public void setServicioMedioPago(IServicioMedioPago servicioMedioPago) {
        this.servicioMedioPago = servicioMedioPago;
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        String idMedioPagoStr = request.getParameter("id");
        Integer idMedioPago = Integer.parseInt(idMedioPagoStr);
        MedioPago medioPago = servicioMedioPago.obtenerMedioPagoId(idMedioPago);
        return medioPago;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response,
            Object command, BindException errors) {
        MedioPago registro = (MedioPago) command;
        ModelAndView mv = new ModelAndView(new RedirectView(getSuccessView()));
        try {
            servicioMedioPago.editarMedioPago(registro);
            mv.addObject("mensaje", "Medio de pago: " + registro.getNombre() + " actualizado con exito");
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
