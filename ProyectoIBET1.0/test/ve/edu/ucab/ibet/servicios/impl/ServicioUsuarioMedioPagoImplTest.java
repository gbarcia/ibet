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
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
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
        Double nuevoMonto = 1500.00;
        MedioPago medioPago = new MedioPago(2, "mastercard", Boolean.TRUE);
        Boolean expResult = true;
        Boolean result = true;
        servicioUsuarioMedioPago.ActualizarMontoMaximo(nuevoMonto, medioPago);
        assertEquals(expResult, result);
    }

    /**
     * Test of ActivarMedioPago method, of class ServicioUsuarioMedioPagoImpl.
     */
//    @Test
    public void testActivarMedioPago() {
        System.out.println("ActivarMedioPago");
        MedioPago medioPago = new MedioPago(1, "paypal", Boolean.TRUE);
        Double montoMaximo = 1000.0;
        Boolean expResult = true;
        Boolean result = true;
        servicioUsuarioMedioPago.ActivarMedioPago(medioPago, montoMaximo);
        assertEquals(expResult, result);
    }

    /**
     * Test of DesactivarMedioPago method, of class ServicioUsuarioMedioPagoImpl.
     */
//    @Test
    public void testDesactivarMedioPago() {
        System.out.println("DesactivarMedioPago");
        MedioPago medioPago = new MedioPago(1, "paypal", Boolean.TRUE);
        Boolean expResult = true;
        Boolean result = true;
        servicioUsuarioMedioPago.DesactivarMedioPago(medioPago);
        assertEquals(expResult, result);
    }

    /**
     * Test of mostrarHistorialMedioPago method, of class ServicioUsuarioMedioPagoImpl.
     */
    @Test
    public void testMostrarHistorialMedioPago() {
        System.out.println("mostrarHistorialMedioPago");
        List<UsuarioMedioPago> result = servicioUsuarioMedioPago.mostrarHistorialMedioPago();
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
        ServicioUsuarioMedioPagoImpl instance = new ServicioUsuarioMedioPagoImpl();
        instance.enviarCorreoNotificacion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getServicioMail method, of class ServicioUsuarioMedioPagoImpl.
     */
//    @Test
    public void testGetServicioMail() {
        System.out.println("getServicioMail");
        ServicioUsuarioMedioPagoImpl instance = new ServicioUsuarioMedioPagoImpl();
        IMailService expResult = null;
        IMailService result = instance.getServicioMail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setServicioMail method, of class ServicioUsuarioMedioPagoImpl.
     */
//    @Test
    public void testSetServicioMail() {
        System.out.println("setServicioMail");
        IMailService servicioMail = null;
        ServicioUsuarioMedioPagoImpl instance = new ServicioUsuarioMedioPagoImpl();
        instance.setServicioMail(servicioMail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}