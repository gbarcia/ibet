package ve.edu.ucab.ibet.controllers.views.gestioncategorias;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ve.edu.ucab.ibet.dominio.Categoria;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioCategoria;

/**
 * Clase controladora para el home de la gestion de categorias
 * @author Gerardo Barcia
 * @version 1.0
 */
public class HomeCategoriaController implements Controller {

    private IServicioCategoria servicioCategoria;
    private IHelperProperties herlperProp;

    public IServicioCategoria getServicioCategoria() {
        return servicioCategoria;
    }

    public void setServicioCategoria(IServicioCategoria servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }

    public IHelperProperties getHerlperProp() {
        return herlperProp;
    }

    public void setHerlperProp(IHelperProperties herlperProp) {
        this.herlperProp = herlperProp;
    }
    
    @SuppressWarnings("unchecked")
    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        
        List<Categoria> listaCategoria = servicioCategoria.listarCategorias();
        ModelAndView mv = new ModelAndView("privado/back/categoria/homeCategoria");
        mv.addObject("categoriaList", listaCategoria);
        return mv;
    }
}
