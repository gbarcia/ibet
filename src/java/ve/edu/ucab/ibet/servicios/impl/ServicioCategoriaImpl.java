package ve.edu.ucab.ibet.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import ve.edu.ucab.ibet.controllers.forms.RegistroCategoriaTO;
import ve.edu.ucab.ibet.dominio.Categoria;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
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
            if (categoria.getIdCategoria() == null && categoria.isHabilitada()) {
                categoriasPadre.add(categoria);
            }
        }
        return categoriasPadre;
    }

    @SuppressWarnings("unchecked")
    public List<Categoria> obtenerSubcategoriasDeUnaCategoria(Categoria categoriaPadre) {
        List<Categoria> subcategorias = new ArrayList<Categoria>();
        String query = "select a from Categoria c inner join c.categoriaCollection " +
                "as a where a.habilitada = true and a.idCategoria.id = ?";
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

    public void agregarCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new ExcepcionNegocio("categoria.invalida");
        }
        Integer id = genericDao.getNextId(categoria);
        categoria.setId(id);
        categoria.setHabilitada(Boolean.TRUE);
        genericDao.insertar(categoria);
    }

    public void editarCategotia(Categoria categoria) {
        if (categoria == null) {
            throw new ExcepcionNegocio("categoria.invalida");
        }
        categoria.setHabilitada(Boolean.TRUE);
        genericDao.merge(categoria);
    }

    public Categoria obtenerCategoria(Integer idCategoria) {
        Categoria categoria = null;
        categoria = (Categoria) genericDao.findByPropertyUnique(Categoria.class, "id", idCategoria);
        if (categoria == null) {
            throw new ExcepcionNegocio("catrgoria.noexiste");
        }
        return categoria;
    }

    public void inhabilitarCategoria(Integer idCategoria) {
        Categoria categoria = null;
        categoria = obtenerCategoria(idCategoria);
        categoria.setHabilitada(Boolean.FALSE);
        genericDao.merge(categoria);
    }

    public Categoria transferObjectToCategoria(RegistroCategoriaTO registro) {
        Categoria categoriaPadre = null;
        if (!registro.getJerarquia().equals("Primer Nivel")) {
            categoriaPadre = obtenerCategoriaPorNombre(registro.getJerarquia());
        }
        Boolean hayEmpate = (registro.getEmpate().equals("SI")) ? Boolean.TRUE : Boolean.FALSE;
        Boolean hayLogica = (registro.getLogicaAutomatica().equals("SI")) ? Boolean.TRUE : Boolean.FALSE;
        Categoria categoria = new Categoria();
        categoria.setEmpate(hayEmpate);
        categoria.setLogicaAutomatica(hayLogica);
        categoria.setNombre(registro.getNombreCategoria());
        categoria.setNombreLogica(registro.getNombreLogica());
        if (categoriaPadre != null) {
            categoria.setIdCategoria(categoriaPadre);
        }
        return categoria;
    }

    public RegistroCategoriaTO categoriaToTransferObject(Categoria categoria) {
        Categoria catJerarquia = null;
        RegistroCategoriaTO registro = new RegistroCategoriaTO();
        String hayEmpate = (categoria.getEmpate()) ? "SI" : "NO";
        String logicaAutomatica = (categoria.getLogicaAutomatica()) ? "SI" : "NO";
        if (categoria.getIdCategoria() != null) {
            catJerarquia = obtenerCategoria(categoria.getIdCategoria().getId());
        }
        String jerarquia = (catJerarquia == null) ? "Primer Nivel" : catJerarquia.getNombre();
        registro.setEmpate(hayEmpate);
        registro.setLogicaAutomatica(logicaAutomatica);
        registro.setNombreCategoria(categoria.getNombre());
        registro.setNombreLogica(categoria.getNombreLogica());
        registro.setJerarquia(jerarquia);
        return registro;
    }

    public Categoria obtenerCategoriaPorNombre(String nombreCategoria) {
        Categoria categoria = null;
        categoria = (Categoria) genericDao.findByPropertyUnique(Categoria.class, "nombre", nombreCategoria);
        if (categoria == null) {
            throw new ExcepcionNegocio("catrgoria.noexiste");
        }
        return categoria;
    }

    public void habilitarCategoria(Integer idCategoria) {
        Categoria categoria = null;
        categoria = obtenerCategoria(idCategoria);
        categoria.setHabilitada(Boolean.TRUE);
        genericDao.merge(categoria);
    }

    @SuppressWarnings("unchecked")
    public List<Categoria> obtenerCategoriasHijos() {
        List<Categoria> resultado = new ArrayList<Categoria>();
        List<Categoria> listaCatComun = new ArrayList<Categoria>();
        String query = "Select c from Categoria c where c.idCategoria is not null and c.habilitada = 1";
        resultado = genericDao.ejecutarQueryList(query);      
        return resultado;
    }

    public Categoria obtenerCategoriaPadrePorCategoria(String nombreCategoria) {
        Categoria categoriaPadre = null;
        Categoria categoria = obtenerCategoriaPorNombre(nombreCategoria);
        categoriaPadre = obtenerCategoria(categoria.getIdCategoria().getId());
        return categoriaPadre;
    }
}
