package ve.edu.ucab.ibet.servicios.interfaces;

import java.util.List;
import ve.edu.ucab.ibet.controllers.forms.RegistroCategoriaTO;
import ve.edu.ucab.ibet.dominio.Categoria;

/**
 * Contrato para ofrecer servicios de operaciones de Categorias
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IServicioCategoria {

    /**
     * firma para obtener las categorias padres
     * @return lista con las categorias padres
     */
    public List<Categoria> obtenerCategoriasPadres();

    /**
     * firma para obtener las subcategorias de una categoria
     * @param categoriaPadre la categoria padre de las subcategorias a buscar
     * @return lista con las subcategorias de la categoria dada
     */
    public List<Categoria> obtenerSubcategoriasDeUnaCategoria(Categoria categoriaPadre);

    /**
     * firma para obtener todas las categorias registradas
     * @return Lista de categorias registradas
     */
    public List<Categoria> listarCategorias();

    /**
     * firma para registrar una nueva categoria
     * @param categoria objeto con la informacion de la categoria a registrar
     */
    public void agregarCategoria(Categoria categoria);

    /**
     * firma para editar una categoria
     * @param categoria objeto con los datos de la categoria
     */
    public void editarCategotia(Categoria categoria);

    /**
     * firma para obtener una categoria en particular
     * @param idCategoria id de la categoria a obtener
     * @return objeto Categoria con sus datos
     */
    public Categoria obtenerCategoria(Integer idCategoria);

    /**
     * firma para obtener una categoria por nombre
     * @param nombreCategoria el nombre de la categoria a buscar
     * @return objeto categoria con la informacion
     */
    public Categoria obtenerCategoriaPorNombre(String nombreCategoria);

    /**
     * firma para inhabilitar del sistema una categoria
     * @param idCategoria id de la categoria a deshabilitar
     */
    public void inhabilitarCategoria(Integer idCategoria);

    /**
     * firma para habilitar una categoria
     * @param idCategoria id de la categoria a habilitar
     */
    public void habilitarCategoria(Integer idCategoria);

    /**
     * Firma para convertir un transferObject en un objeto de dominio categoria
     * @param registro objeto transfer object a convertir
     * @return Objeto Categoria
     */
    public Categoria transferObjectToCategoria(RegistroCategoriaTO registro);

    /**
     * firma para convertir un objeto categoria en un transfer object
     * @param categoria objeto categoria a convertir
     * @return objet de transferencia
     */
    public RegistroCategoriaTO categoriaToTransferObject(Categoria categoria);

    /**
     * firma para obtener todas las categorias de segundo nivel en la jerarquia
     * @return Lista de objetos categorias
     */
    public List<Categoria> obtenerCategoriasHijos();

    /**
     * firma para obtener la categoria del nivel superior a una categoria
     * @param nombreCategoria el nombre de la categoria
     * @return Objeto Categoria de nivel superior
     */
    public Categoria obtenerCategoriaPadrePorCategoria(String nombreCategoria);
}
