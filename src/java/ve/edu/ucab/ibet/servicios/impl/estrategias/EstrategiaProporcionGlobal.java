package ve.edu.ucab.ibet.servicios.impl.estrategias;

import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.ProporcionPago;
import ve.edu.ucab.ibet.servicios.interfaces.estrategias.EstrategiaProporcion;

/**
 * Clase para establer la estrategia de proporcion global, mediante esta estrategia
 * de negocio, se fija por antelacion los pagos para ambos participantes y el empate
 * @author Gerardo Barcia
 * @version 1.0
 */
public class EstrategiaProporcionGlobal implements EstrategiaProporcion {

    public static final Double PROPORCION_EQUIPO_UNO = 1.0;
    public static final Double PROPORCION_EQUIPO_DOS = 3.0;
    public static final Double PROPORCION_EMPATE = 1.0;

    public ProporcionPago fijarProporcion(Participante p1, Participante p2) {
        ProporcionPago proporcion = new ProporcionPago();
        proporcion.setPagoPorEmpate(PROPORCION_EMPATE);
        proporcion.setPagoPorGanar(PROPORCION_EQUIPO_UNO);
        proporcion.setPagoPorPerder(PROPORCION_EQUIPO_DOS);
        return proporcion;
    }
}
