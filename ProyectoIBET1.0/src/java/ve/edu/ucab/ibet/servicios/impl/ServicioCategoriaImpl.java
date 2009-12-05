package ve.edu.ucab.ibet.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import ve.edu.ucab.ibet.dominio.Categoria;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioCategoria;

/**
 * Clase para ofrecer servicios para manejo de categorias
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ServicioCategoriaImpl implements IServicioCategoria {

    private IGenericDao genericDao;
    private IHelperProperties helperProp;

    public IGenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(IGenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public IHelperProperties getHelperProp() {
        return helperProp;
    }

    public void setHelperProp(IHelperProperties helperProp) {
        this.helperProp = helperProp;
    }

    @SuppressWarnings("unchecked")
    public List<Categoria> obtenerCategoriasPadres() {
        List<Categoria> todasLasCategorias = new ArrayList<Categoria>();
        todasLasCategorias.addAll(genericDao.listar(Categoria.class));

        List<Categoria> categoriasPadre = new ArrayList<Categoria>();
        for (Categoria categoria : todasLasCategorias) {
            if (categoria.getIdCategoria() == null) {
                categoriasPadre.add(categoria);
            }
        }
        return categoriasPadre;
    }
    @SuppressWarnings("unchecked")
    public List<Categoria> obtenerSubcategoriasDeUnaCategoria(Categoria categoriaPadre) {
        List<Categoria> subcategorias = new ArrayList<Categoria>();
        String query = "select c.categoriaCollection from Categoria c where c.id = ?";
        Object[] parametros = {categoriaPadre.getId()};
        subcategorias.addAll(this.genericDao.ejecutarQueryList(query, parametros));
        return subcategorias;
    }

    @SuppressWarnings("unchecked")
    public List<Categoria> listarCategorias() {
        List<Categoria> lista = new ArrayList<Categoria>();
        lista = genericDao.listar(Categoria.class);
        return lista;
    }
}
