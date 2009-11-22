package ve.edu.ucab.ibet.servicios.interfaces;

import java.util.List;
import ve.edu.ucab.ibet.dominio.Evento;

/**
 * Servicio para la gestion de eventos
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IServicioEvento {

    /**
     * firma para obtener los eventos de una categoria
     * @param categoria la categoria a la cual consultaremos sus eventos
     * @return lista de eventos de la categoria indicada
     */
    public List<Evento> obtenerEventosDeUnaCategoria(Integer idSubcategoria);

}
