package ve.edu.ucab.ibet.controllers.views.gestioncategorias;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.dominio.Categoria;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioCategoria;

/**
 * Clase para soportar el request para habilitar una categoria y redirigir
 * al home de categoria mostrando un mensaje de exito o fallo de la operacion
 * @author Gerardo Barcia
 * @version 1.0
 */
public class HabilitarCategoriaController implements Controller {

    private IServicioCategoria servicioCategoria;

    public IServicioCategoria getServicioCategoria() {
        return servicioCategoria;
    }

    public void setServicioCategoria(IServicioCategoria servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        String idCategoriaStr = req.getParameter("id");
        Integer idCategoria = Integer.parseInt(idCategoriaStr);
        servicioCategoria.habilitarCategoria(idCategoria);
        List<Categoria> listaCategoria = servicioCategoria.listarCategorias();
        ModelAndView mv = new ModelAndView(new RedirectView("/ProyectoIBET/privado/back/categoria/homeCategoria.htm"));
        mv.addObject("mensaje","Categoria habilitada con exito");
        mv.addObject("categoriaList", listaCategoria);
        return mv;
    }

}
