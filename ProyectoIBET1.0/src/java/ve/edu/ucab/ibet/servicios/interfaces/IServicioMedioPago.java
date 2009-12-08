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

    /**
     * firma para obtener un medio pago 
     * @param nombre Nombre del medio pago a buscar
     * @return El medio pago obtenido en la consulta 
     */
    public MedioPago obtenerMedioPago(String nombre);

    /**
     * firma para crear un medio de pago
     * @param medioPago el medio de pago a crear 
     */
    public void crearMedioPago(MedioPago medioPago);

    /**
     * firma para editar un medio de pago
     * @param medioPago el medio de pago a editar 
     */
    public void editarMedioPago(MedioPago medioPago);

    /**
     * firma para inhabilitar un medio de pago
     * @param medioPago el medio de pago a inhabilitar
     */
    public void inhabilitarMedioPago(MedioPago medioPago);

    /**
     * firma para habilitar medio de pago
     * @param medioPago el medio de pago a habilitar
     */
    public void habilitarMedioPago(MedioPago medioPago);

}
