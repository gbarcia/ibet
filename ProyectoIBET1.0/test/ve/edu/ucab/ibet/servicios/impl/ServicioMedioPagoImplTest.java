/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.ibet.servicios.impl;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import static org.junit.Assert.*;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioMedioPago;

/**
 *
 * @author nath
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/config/hibernate/applicationContextTest.xml"})
public class ServicioMedioPagoImplTest {

    @Autowired
    private IServicioMedioPago servicioMedioPago;

    public ServicioMedioPagoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

//    @Test
    public void testObtenerMediosPagoVigentes() {
        System.out.println("obtenerMediosPagoVigentes");
        List<MedioPago> result = servicioMedioPago.obtenerMediosPagoVigentes();
        assertNotNull(result);
        for (MedioPago medioPago : result) {
            System.out.println(medioPago.getNombre());
        }
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

//    @Test
    public void testObtenerMedioPago() {
        System.out.println("obtenerMedioPago");
        MedioPago result = servicioMedioPago.obtenerMedioPago("paypal");
        assertNotNull(result);
        System.out.println("Medio pago: " + result.getId() + ", " + result.getNombre() + ", " + result.getActivo());
    }

//    @Test
    public void testCrearMedioPago() {
        System.out.println("crearMedioPago");
        MedioPago medioPago = new MedioPago();
        medioPago.setNombre("pago");
        servicioMedioPago.crearMedioPago(medioPago);
        assertTrue(Boolean.TRUE);
    }

//    @Test
    public void testEditarMedioPago() {
        System.out.println("editarMedioPago");
        MedioPago medioPago = new MedioPago(4, "pagoNuevo", Boolean.FALSE);
        servicioMedioPago.editarMedioPago(medioPago);
        assertTrue(Boolean.TRUE);
    }

//    @Test
    public void testInhabilitarMedioPago() {
        System.out.println("inhabilitarMedioPago");
        MedioPago medioPago = new MedioPago(4, "pagoNuevo", Boolean.FALSE);

        try {
            servicioMedioPago.inhabilitarMedioPago(medioPago);
        } catch (GeneralException e) {
            System.out.println(e.getKeyError());
        }
        
        assertTrue(Boolean.TRUE);
    }

    @Test
    public void testHabilitarMedioPago() {
        System.out.println("habilitarMedioPago");
        MedioPago medioPago = new MedioPago(4, "pagoNuevo", Boolean.TRUE);

        try {
            servicioMedioPago.habilitarMedioPago(medioPago);
        } catch (GeneralException e) {
            System.out.println(e.getKeyError());
        }

        assertTrue(Boolean.TRUE);
    }
}
