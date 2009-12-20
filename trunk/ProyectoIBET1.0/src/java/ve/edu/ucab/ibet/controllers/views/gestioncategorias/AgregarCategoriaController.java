package ve.edu.ucab.ibet.controllers.views.gestioncategorias;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.controllers.forms.RegistroCategoriaTO;
import ve.edu.ucab.ibet.dominio.Categoria;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.generic.util.DatosUtil;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioCategoria;

/**
 * Clase para mostrar y procesar el formulario para registro de categoria
 * @author Gerardo Barcia
 * @version 1.0
 */
public class AgregarCategoriaController extends SimpleFormController {

    private IServicioCategoria servicioCategoria;

    public IServicioCategoria getServicioCategoria() {
        return servicioCategoria;
    }

    public void setServicioCategoria(IServicioCategoria servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }

    public AgregarCategoriaController() {
        setCommandClass(RegistroCategoriaTO.class);
        setCommandName("registroCategoria");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        RegistroCategoriaTO registro = new RegistroCategoriaTO();
        registro.setNombreCategoria(new String());
        registro.setEmpate("SI");
        registro.setLogicaAutomatica("NO");
        registro.setNombreLogica(null);
        return registro;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request) {
        Map referenceData = new HashMap();
        List<String> listaNombresCatPadres = new ArrayList<String>();
        listaNombresCatPadres.add("Primer Nivel");
        for (Categoria categoria : servicioCategoria.obtenerCategoriasPadres()) {
            listaNombresCatPadres.add(categoria.getNombre());
        }
        referenceData.put("opcionJerarquia", listaNombresCatPadres);
        referenceData.put("opcionBooleana", DatosUtil.BOOLEANOS);
        return referenceData;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest req, HttpServletResponse resp,
            Object command, BindException errors) {
        String atributoError = null;
        RegistroCategoriaTO registro = (RegistroCategoriaTO) command;
        Categoria categoria = servicioCategoria.transferObjectToCategoria(registro);
        ModelAndView mv = new ModelAndView(new RedirectView(getSuccessView()));
        try {
            servicioCategoria.agregarCategoria(categoria);
            mv.addObject("mensaje", "Categoria "+ categoria.getNombre() + " registrada con exito");
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            mv = new ModelAndView("errorDA");
        } catch (GeneralException ge) {
            ge.printStackTrace();
            errors.rejectValue(atributoError, ge.getKeyError());
            mv = showForm(req, resp, errors);
        } finally {
            return mv;
        }
    }
}
