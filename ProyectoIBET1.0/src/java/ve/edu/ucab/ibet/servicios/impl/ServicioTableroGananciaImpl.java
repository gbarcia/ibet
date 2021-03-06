package ve.edu.ucab.ibet.servicios.impl;

import java.util.List;
import ve.edu.ucab.ibet.dominio.Categoria;
import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioCategoria;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioTableroGanancia;

/**
 * Clase concreta de implementacion de servicios para TableroGanancia
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ServicioTableroGananciaImpl implements IServicioTableroGanancia {

    private IGenericDao genericDao;
    private IServicioCategoria servicioCategoria;

    public IGenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(IGenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public IServicioCategoria getServicioCategoria() {
        return servicioCategoria;
    }

    public void setServicioCategoria(IServicioCategoria servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }

    public void agregarTableroGanancia(TableroGanancia tablero) {
        if (tablero == null) {
            throw new ExcepcionNegocio("tableroganancia.invalido");
        }
        tablero.setEmpato(null);
        tablero.setGano(null);
        genericDao.insertar(tablero);
    }

    public void actualizarTableroGanancia(TableroGanancia tablero) {
        if (tablero == null) {
            throw new ExcepcionNegocio("tableroganancia.invalido");
        }
        genericDao.merge(tablero);
    }

    @SuppressWarnings("unchecked")
    public List<Participante> obtenerParticipantesPorCategoria(String nombreCategoria) {
        List<Participante> listaParticipantes = null;
        String nombreCategoriaQuery = nombreCategoria;
        Categoria categoria = servicioCategoria.obtenerCategoriaPorNombre(nombreCategoria);
        if (categoria.isParticipantesComun()) {
            Categoria categoriaPadre = servicioCategoria.obtenerCategoriaPadrePorCategoria(nombreCategoria);
            nombreCategoriaQuery = categoriaPadre.getNombre();
        }
        Object[] o = {nombreCategoriaQuery};
        String query = "Select p from Participante p where p.idCategoria.nombre = ? ";
        listaParticipantes = genericDao.ejecutarQueryList(query, o);
        return listaParticipantes;
    }

    @SuppressWarnings("unchecked")
    public Integer cantidadDeEventosGanadosPorParticipante(Integer idParticipante) {
        Integer resultado = 0;
        Object[] o = {Boolean.TRUE, idParticipante};
        String query = "select t from TableroGanancia t where t.gano = ? and t.participante.id = ?";
        List<TableroGanancia> lista = genericDao.ejecutarQueryList(query, o);
        resultado = lista.size();
        return resultado;
    }
}
