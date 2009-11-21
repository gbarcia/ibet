package ve.edu.ucab.ibet.servicios.interfaces;

import java.util.List;
import ve.edu.ucab.ibet.dominio.Categoria;

/**
 * Servicio para la gestion de categorias
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IServicioCategoria {

    public List<Categoria> obtenerCategorias();

    public List<Categoria> obtenerSubcategoriasDeUnaCategoria(Categoria categoriaPadre);

}
