/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.ibet.controllers.views;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ve.edu.ucab.ibet.dominio.Categoria;
import ve.edu.ucab.ibet.dominio.to.views.ArbolCategoriaTO;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioCategoria;

/**
 * Controlador para crear el menu de las categorias
 * @author jonathan
 */
public class MenuCategoriasController implements Controller {

    private IServicioCategoria servicioCategoria;

    public IServicioCategoria getServicioCategoria() {
        return servicioCategoria;
    }

    public void setServicioCategoria(IServicioCategoria servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }

    public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView mv = new ModelAndView("/include/sideMenu");
        List<Categoria> categoriasPadre = this.servicioCategoria.obtenerCategoriasPadres();

        List<ArbolCategoriaTO> listaArbolCategorias = new ArrayList<ArbolCategoriaTO>();
        for (Categoria categoria : categoriasPadre) {
            ArbolCategoriaTO arbolCategoria = new ArbolCategoriaTO();

            arbolCategoria.setCategoriaPadre(categoria);
            
            List<Categoria> subcategorias = new ArrayList<Categoria>();
            subcategorias = this.servicioCategoria.obtenerSubcategoriasDeUnaCategoria(categoria);
            arbolCategoria.setListaSubcategorias(subcategorias);
            
            listaArbolCategorias.add(arbolCategoria);
        }

        mv.addObject("arbolCategoria", listaArbolCategorias);
        return mv;
    }
}
