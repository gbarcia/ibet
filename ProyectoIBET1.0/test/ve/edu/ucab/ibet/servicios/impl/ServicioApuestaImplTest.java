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
import ve.edu.ucab.ibet.dominio.Apuesta;
import static org.junit.Assert.*;
import ve.edu.ucab.ibet.dominio.Evento;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioApuesta;

/**
 * Clase de pruebas unitarias para realizar Apuestas y sus validaciones
 * @author Gerardo Barcia
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/config/hibernate/applicationContextTest.xml"})
public class ServicioApuestaImplTest {

    @Autowired
    private IServicioApuesta servicioApuesta;

    public ServicioApuestaImplTest() {
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
     * Test of esValidaApuestaUsuario method, of class ServicioApuestaImpl.
     */
    //@Test
//    public void testEsValidaApuestaUsuario() {
//        System.out.println("esValidaApuestaUsuario");
//        Users usuario = new Users("gerardo");
//        TableroGanancia tablero= new TableroGanancia(1, 2);
//        tablero.setEvento(new Evento(2));
//        try {
//        servicioApuesta.esValidaApuestaUsuario(usuario, tablero);
//        }catch (GeneralException e) {
//            System.out.println("des:" + e.getKeyError());
//        }
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

    /**
     * Test of realizarApuesta method, of class ServicioApuestaImpl.
     */
    //@Test
//    public void testRealizarApuesta() {
//        System.out.println("realizarApuesta");
//        Users usuario = null;
//        TableroGanancia tablero = null;
//        ServicioApuestaImpl instance = new ServicioApuestaImpl();
//        instance.realizarApuesta(usuario, tablero);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     *Verifica si un usuario ya ha apostado en un determinado evento
     */
    //@Test
//    public void testUsuarioHaApostadoEvento() {
//        System.out.println("usuarioHaApostadoEvento");
//        Users usuario = new Users("gerardo");
//        Boolean expResult = false;
//        Evento evento = new Evento(2);
//        Boolean result = servicioApuesta.usuarioHaApostadoEvento(usuario, evento);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }
//
//}

//    //@Test
//    public void testUsuarioPoseeMetodosPago() {
//        System.out.println("usuarioPoseeMetodosPAGO");
//        Users usuario = new Users("gerardo");
//        Boolean expResult = false;
//        Boolean result = servicioApuesta.usuarioPoseeMetodosPago(usuario);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

//      @Test
//    public void testComprobarMontoMaximo() {
//        System.out.println("Comprobar monto maximo");
//        Users usuario = new Users("gerardo");
//        Boolean expResult = false;
//        MedioPago medio = new MedioPago(3);
//        Double montoApostado = new Double(400);
//        Boolean result = servicioApuesta.montoMaximoPagoUsuarioAprobado(usuario, medio,montoApostado);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

//          @Test
//    public void testPeriodoApuestaVigente() {
//        System.out.println("Es periodo apuesta vogente");
//        Boolean expResult = true;
//        TableroGanancia tablero = new TableroGanancia(1, 1);
//        Boolean result = servicioApuesta.esPeriodoDeApuestaVigente(tablero);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

//              @Test
//    public void testMontoActualApuesta() {
//        System.out.println("Monto actual apuesta");
//        TableroGanancia tablero = new TableroGanancia(1, 1);
//        Double resultado = servicioApuesta.obtenerCantidadApostadaParaEvento(tablero);
//        System.out.println(resultado);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

   @Test
    public void testHacerApuesta() {
        System.out.println("realizar Apuesta");
        Users usuarioApuesta = new Users("gerardo");
        usuarioApuesta.setNombre("Gerardo");
        usuarioApuesta.setApellido("Barcia");
        usuarioApuesta.setCorreo("mayita.uribe@gmail.com");
        usuarioApuesta.setSexo("M");
        MedioPago medioPagoApuesta = new MedioPago(3);
        TableroGanancia tablero = new TableroGanancia(2, 3);
        Participante p = new Participante(3,"Real Madrid");
        tablero.setPropocionGano(12);
        tablero.setParticipante(p);
        Evento evento = new Evento(2);
        tablero.setEvento(evento);
        Apuesta apuesta = new Apuesta();
        apuesta.setEmpato(Boolean.TRUE);
        apuesta.setFecha(UtilMethods.convertirFechaFormato(new java.util.Date()));
        apuesta.setGano(Boolean.FALSE);
        apuesta.setMedioPago(medioPagoApuesta);
        apuesta.setMonto(new Double(10));
        apuesta.setTableroGanancia(tablero);
        apuesta.setUsers(usuarioApuesta);
        try {
        servicioApuesta.realizarApuesta(apuesta);
        }catch (GeneralException e) {
            System.out.println(e.getKeyError());
        }
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}