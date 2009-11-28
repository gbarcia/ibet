package ve.edu.ucab.ibet.servicios.interfaces;

import java.util.List;
import ve.edu.ucab.ibet.dominio.Evento;
import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.Politica;
import ve.edu.ucab.ibet.dominio.TableroGanancia;

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

    /**
     * firma para obtener un evento a partir de un tablero ganancia
     * @param tablero el objeto tablero de ganancia
     * @return Objeto evento asociado al tablero ganancia
     */
    public Evento obtenerEventoporTableroGanancia (TableroGanancia tablero);

    /**
     * firma para obtener la politica de un evento
     * @param evento objeto evento a obtener su politica
     * @return objeto con la Politica del evento
     */
    public Politica obtenerPoliticaParaEvento(Evento evento);

    /**
     * firma para obtener los proximos eventos en ocurrir
     * @return lista de eventos
     */
    public List<Evento> obtenerProximosEventos();

    /**
     * firma para obtener los proximos eventos en ocurrir que tienen una imagen
     * @return lista de eventos
     */
    public List<Evento> obtenerProximosEventosConImagen();

    /**
     * firma para buscar un evento por su id
     * @param idEvento el id del evento
     * @return el evento
     */
    public Evento obtenerEvento(Integer idEvento);

    /**
     * firma para busca un participante por su id
     * @param idParticipante el id del participante
     * @return el participante
     */
    public Participante obtenerParticipante(Integer idParticipante);


}