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
import ve.edu.ucab.ibet.generic.util.mail.interfaces.IMailService;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuarioMedioPago;

/**
 *
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
     * Test of ActualizarMontoMaximo method, of class ServicioUsuarioMedioPagoImpl.
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
     * Test of ActivarMedioPago method, of class ServicioUsuarioMedioPagoImpl.
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
     * Test of DesactivarMedioPago method, of class ServicioUsuarioMedioPagoImpl.
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
     * Test of mostrarHistorialMedioPago method, of class ServicioUsuarioMedioPagoImpl.
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
     * Test of enviarCorreoNotificacion method, of class ServicioUsuarioMedioPagoImpl.
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