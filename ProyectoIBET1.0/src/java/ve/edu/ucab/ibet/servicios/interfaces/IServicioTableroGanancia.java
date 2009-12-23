package ve.edu.ucab.ibet.servicios.interfaces;

import java.util.List;
import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.TableroGanancia;

/**
 * Clase para exponer servicios de manipulacion de tableros de ganancia para
 * los eventos y su relacion con los participantes
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IServicioTableroGanancia {

    /**
     * firma para registrar un tablero de ganancia para un participante en un
     * determinado evento
     * @param tablero Objeto tablero con la informacion de proporciones y evento
     */
    public void agregarTableroGanancia(TableroGanancia tablero);

    /**
     * firma para actualizar un tablero de ganancia para un participante en un
     * evento determinado
     * @param tablero Objeto tablero con la informacion de proporciones y evento
     */
    public void actualizarTableroGanancia(TableroGanancia tablero);

    /**
     * firma para obtener una lista de participantes por categoria
     * @param nombreCategoria el nombre de la categoria a obtener los participantes
     * @return Lista de objertos Participante con la informacion
     */
    public List<Participante> obtenerParticipantesPorCategoria (String nombreCategoria);
}
