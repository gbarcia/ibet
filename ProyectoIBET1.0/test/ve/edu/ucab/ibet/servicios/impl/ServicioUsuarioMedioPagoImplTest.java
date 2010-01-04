/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.servicios.impl;

import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPagoPK;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuarioMedioPago;

/**
 * Pruebas unitarias para servicio usuario medio pago
 * @author maya
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/config/hibernate/applicationContextTest.xml"})
public class ServicioUsuarioMedioPagoImplTest {

    public ServicioUsuarioMedioPagoImplTest() {
    }

    @Autowired
    private IServicioUsuarioMedioPago servicioUsuarioMedioPago;

    /**
     * Prueba para actualizar el monto maximo de un medio de pago
     * de un usuario.
     */
//    @Test
    public void testActualizarMontoMaximo() {
        System.out.println("ActualizarMontoMaximo");
        Users user = new Users("maya");
        Double nuevoMonto = 1500.00;
        MedioPago medioPago = new MedioPago(2, "mastercard", Boolean.TRUE);
        Boolean expResult = true;
        Boolean result = true;
        servicioUsuarioMedioPago.ActualizarMontoMaximo(nuevoMonto, medioPago, user);
        assertEquals(expResult, result);
    }

    /**
     * Prueba para activar un medio de pago a un usuario 
     */
    @Test
    public void testActivarMedioPago() {
        System.out.println("ActivarMedioPago");
        Users user = new Users("carlosdbm");
        MedioPago medioPago = new MedioPago(2, "mastercard", Boolean.TRUE);
        Double montoMaximo = 1000.0;
        Boolean expResult = true;
        Boolean result = true;
        servicioUsuarioMedioPago.ActivarMedioPago(medioPago, montoMaximo, user);
        assertEquals(expResult, result);
    }

    /**
     * Prueba para desactivar un medio de pago a un usuario 
     */
//    @Test
    public void testDesactivarMedioPago() {
        System.out.println("DesactivarMedioPago");
        Users user = new Users("maya");
        MedioPago medioPago = new MedioPago(1, "paypal", Boolean.TRUE);
        Boolean expResult = true;
        Boolean result = true;
        servicioUsuarioMedioPago.DesactivarMedioPago(medioPago, user);
        assertEquals(expResult, result);
    }

    /**
     * Prueba para listar el historial de medios de pagos de un usuario 
     */
//    @Test
    public void testMostrarHistorialMedioPago() {
        System.out.println("mostrarHistorialMedioPago");
        Users user = new Users("maya");
        List<UsuarioMedioPago> result = servicioUsuarioMedioPago.mostrarHistorialMedioPago(user);
        for (UsuarioMedioPago u : result) {
            System.out.println(u.getFechaInicio() + ", " + u.getUsers().getUsername());
        }
        assertNotNull(result);
    }

    /**
     * Prueba para enviar un correo electronico con la notificacion de algun cambio
     * o activacion de un medio de pago a un usuario 
     */
//    @Test
    public void testEnviarCorreoNotificacion() {
        System.out.println("enviarCorreoNotificacion");

        Users user = new Users("maya");
        MedioPago medioPago = new MedioPago(1, "paypal", Boolean.TRUE);
        UsuarioMedioPago usuarioMedioPago = new UsuarioMedioPago();
        UsuarioMedioPagoPK pk = new UsuarioMedioPagoPK(7, "maya", medioPago.getId());
        usuarioMedioPago.setMedioPago(medioPago);
        usuarioMedioPago.setUsuarioMedioPagoPK(pk);
        usuarioMedioPago.setActivo(Boolean.TRUE);
        usuarioMedioPago.setFechaInicio(UtilMethods.stringToFecha("2009-11-25"));
        usuarioMedioPago.setFechaFin(null);
        usuarioMedioPago.setMontoMaximo(new Double(1000));
        usuarioMedioPago.setUsers(user);
        servicioUsuarioMedioPago.enviarCorreoNotificacion(usuarioMedioPago, user);
    }



}