package ve.edu.ucab.ibet.servicios.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioTableroGanancia;

/**
 * Clase para pruebas de servicios de tablero ganancia
 * @author Gerardo Barcia
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/config/hibernate/applicationContextTest.xml"})
public class ServicioTableroGananciaImplTest {

    @Autowired
    private IServicioTableroGanancia servicio;

    public ServicioTableroGananciaImplTest() {
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

    /**
     * Prueba para obtener la cantida de eventos dado un participante
     */
    @Test
    public void testCantidadDeEventosGanadosPorParticipante() {
        System.out.println("cantidadDeEventosGanadosPorParticipante");
        Integer idParticipante = 2;
        Integer expResult = 1;
        Integer result = servicio.cantidadDeEventosGanadosPorParticipante(idParticipante);
        assertEquals(expResult, result);
        System.out.println(result);
    }

}