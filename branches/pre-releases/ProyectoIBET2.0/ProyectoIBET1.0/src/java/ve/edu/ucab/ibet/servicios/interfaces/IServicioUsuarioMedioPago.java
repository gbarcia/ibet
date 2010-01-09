package ve.edu.ucab.ibet.servicios.interfaces;

import java.util.List;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;

/**
 * Interface para el manejo de los metodos de pago de los usuarios
 * @author maya
 */
public interface IServicioUsuarioMedioPago {

    /**
     * Firma para actualizar el monto maximo del medio de pago
     * @param nuevoMonto Nuevo monto a establecer en el medio de pago
     * @param medioPago Medio de pago al que se le desea modificar el monto
     */
    public void ActualizarMontoMaximo(Double nuevoMonto, MedioPago medioPago, Users user);

    /**
     * Firma para activar un medio de pago
     * @param medioPago El medio de pago que se desea activar
     * @return Boolean con el resultado de la operacion
     */
    public void ActivarMedioPago(MedioPago medioPago, Double montoMaximo, Users user);

    /**
     * Firma para desactivar un medio de pago
     * @param medioPago El medio de pago que se desea desactivar
     * @return Boolean con el resultado de la operacion
     */
    public void DesactivarMedioPago(MedioPago medioPago, Users user);

    /**
     * Firma para mostrar el historial de los medio de pagos gestionados por un usuario
     * @return Lista de UsuarioMedioPago con el resultado de la consulta
     */
    public List<UsuarioMedioPago> mostrarHistorialMedioPago(Users user);

    /**
     * Firma para enviar correo electronico con la notificacion
     * @param user Usuario al que se el enviara la notificacion via correo
     */
    public void enviarCorreoNotificacion(UsuarioMedioPago usuarioMedioPago, Users user);

}
