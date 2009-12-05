package ve.edu.ucab.ibet.dominio.to.views;

import java.util.List;
import ve.edu.ucab.ibet.dominio.Categoria;

/**
 * Objeto de transferencia para un arbol de categoria y sus subcategorias
 * @author jonathan
 */
public class ArbolCategoriaTO {

    private Categoria categoriaPadre;
    private List<Categoria> listaSubcategorias;

    public ArbolCategoriaTO() {
    }

    public ArbolCategoriaTO(Categoria categoriaPadre, List<Categoria> listaSubcategorias) {
        this.categoriaPadre = categoriaPadre;
        this.listaSubcategorias = listaSubcategorias;
    }

    public Categoria getCategoriaPadre() {
        return categoriaPadre;
    }

    public void setCategoriaPadre(Categoria categoriaPadre) {
        this.categoriaPadre = categoriaPadre;
    }

    public List<Categoria> getListaSubcategorias() {
        return listaSubcategorias;
    }

    public void setListaSubcategorias(List<Categoria> listaSubcategorias) {
        this.listaSubcategorias = listaSubcategorias;
    }

}
