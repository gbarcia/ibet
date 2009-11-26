package ve.edu.ucab.ibet.servicios.interfaces;

import java.util.List;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;

/**
 * Servicios para la gestion de metodos de pago
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IServicioMedioPago {

    /**
     * firma para obtener todos los metodos de pago con estado activo
     * @return Lista de objetos MedioPago con la condicion de activos
     */
    public List<MedioPago> obtenerMediosPagoVigentes();

    /**
     * firma para obtener los nombres de los metodos de pago vigentes
     * a partir de una lista de metodos de pago
     * @return Lista de strings con los metodos de pago vigentes
     */
    public List<String> obtenerNombresMediosPagoVigentes(List<UsuarioMedioPago> lista);

}
