package ve.edu.ucab.ibet.servicios.impl.estrategias;

import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.ProporcionPago;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioTableroGanancia;
import ve.edu.ucab.ibet.servicios.interfaces.estrategias.EstrategiaProporcion;

/**
 * Clase para establer la estrategia de proporcion estadistica, mediante esta estrategia
 * de negocio, se fija por antelacion los pagos para ambos participantes y el empate
 * mediante las estadisticas de cada equipo en cuanto a victorias  y derrotas.
 * La formula para el calculo de la proporcion se basa en estimar la razon entre
 * ambos participantes mediante sus estadisiticas en la casa de apuestas
 * @author Gerardo Barcia
 * @version 1.0
 */
public class EstrategiaProporcionEstadisitica implements EstrategiaProporcion {

    private IServicioTableroGanancia servicioTableroGanancia;

    public ProporcionPago fijarProporcion(Participante p1, Participante p2) {
        Integer ganadosEquipUno = servicioTableroGanancia.cantidadDeEventosGanadosPorParticipante(p1.getId());
        Integer ganadosEquipDos = servicioTableroGanancia.cantidadDeEventosGanadosPorParticipante(p2.getId());
        Integer maximoComunD = obtenerMaximoComunDivisor(ganadosEquipUno, ganadosEquipDos);        
        Integer propEquipoUno = 3;
        Integer propEquipoDos = 1;
        if (maximoComunD != 0) {
        propEquipoUno = ganadosEquipUno / maximoComunD;
        propEquipoDos = ganadosEquipDos / maximoComunD;
        }
        ProporcionPago proporcion = new ProporcionPago();
        proporcion.setPagoPorEmpate(new Double(1));
        proporcion.setPagoPorGanar(new Double(propEquipoUno));
        proporcion.setPagoPorPerder(new Double(propEquipoDos));
        return proporcion;
    }

    private Integer obtenerMaximoComunDivisor(Integer x, Integer y) {
        Integer menorNumero = x < y ? x : y;
        Integer resultado = menorNumero;
        Integer aux = menorNumero;
        while (aux >= 1) {
            Integer residuos = (((x % aux) + (y % aux)));
            if (residuos == 0) {
                resultado = aux;
                break;
            }
            aux--;
        }
        return resultado;
    }

    public IServicioTableroGanancia getServicioTableroGanancia() {
        return servicioTableroGanancia;
    }

    public void setServicioTableroGanancia(IServicioTableroGanancia servicioTableroGanancia) {
        this.servicioTableroGanancia = servicioTableroGanancia;
    }
}
