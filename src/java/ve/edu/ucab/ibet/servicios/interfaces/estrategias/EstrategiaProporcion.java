package ve.edu.ucab.ibet.servicios.interfaces.estrategias;

import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.ProporcionPago;

/**
 * Contrato para establecer la proporcion de un equipo en determinado evento
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface EstrategiaProporcion {

    /**
     * Firma para fijar la proporcion de apuestas entre dos participantes
     * la propotcion devuelta sera para el primer participante del parametro
     * @param p1 participante uno de la apuesta
     * @param p2 participante dos de la apuesta
     * @return Objeto ProporcionPago para el primer participante del parametro
     */
    public ProporcionPago fijarProporcion(Participante p1, Participante p2);
}
