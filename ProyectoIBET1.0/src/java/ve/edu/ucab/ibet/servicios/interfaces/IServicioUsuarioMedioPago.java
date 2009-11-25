package ve.edu.ucab.ibet.servicios.interfaces;

import java.util.List;
import ve.edu.ucab.ibet.dominio.MedioPago;

/**
 * Interface para el manejo de los metodos de pago de los usuarios
 * @author maya
 */
public interface IServicioUsuarioMedioPago {

    /**
     * Firma para actualizar el monto maximo del medio de pago
     * @param nuevoMonto Nuevo monto a establecer en el medio de pago
     * @param medioPago Medio de pago al que se le desea modificar el monto
     * @return Boolean que indica el resultado de la operacion 
     */
    public Boolean ActualizarMontoMaximo(Double nuevoMonto, MedioPago medioPago);

    /**
     * Firma para activar un medio de pago
     * @param medioPago El medio de pago que se desea activar
     * @return Boolean con el resultado de la operacion
     */
    public Boolean ActivarMedioPago(MedioPago medioPago);

    /**
     * Firma para desactivar un medio de pago
     * @param medioPago El medio de pago que se desea desactivar
     * @return Boolean con el resultado de la operacion
     */
    public Boolean DesactivarMedioPago(MedioPago medioPago);

    /**
     * Firma para mostrar el historial de los medio de pagos gestionados por un usuario
     * @return Lista de MedioPago con el resultado de la consulta
     */
    public List<MedioPago> mostrarHistorialMedioPago();

    /**
     * Firma para enviar correo electronico con la notificacion
     */
    public void enviarCorreoNotificacion();

}
