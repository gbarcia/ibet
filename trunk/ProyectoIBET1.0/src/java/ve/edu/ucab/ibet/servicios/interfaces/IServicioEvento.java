package ve.edu.ucab.ibet.servicios.interfaces;

import java.util.Date;
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
     * firma para obtener los proximos eventos en ocurrir para una categoria
     * @return lista de eventos
     */
    public List<Evento> obtenerProximosEventosDeUnaCategoria(Integer idSubcategoria);

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

    /**
     * firma para obtener el tablero de proporciones de ganancias para un determinado
     * evento y un equipo en particular
     * @param fechaEvento fecha de ejecucion del evento
     * @param nombreEquipo nombre oficial del equipo a conocer su tablero de ganancia
     * @return objeto TableroGanancia con la informacion
     */
    public TableroGanancia obtenerTableroPorEquipoyEvento(Date fechaEvento, String nombreEquipo);

    /**
     * firma para obtener los datos de un participante por su nombre
     * @param nombre nombre del participante a buscar
     * @return objeto participante con la informacion
     */
    public Participante obtenerParticipantePorNombre (String nombre);

    /**
     * firma para finalizar un evento seleccionado 
     * @param idEvento identificador del evento a finalizar
     * @param resultado resultado del evento a finalizar 
     */
    public void finalizarEvento(Integer idEvento, String resultado, Integer idParticipante, Boolean gano, Boolean empato);
}
