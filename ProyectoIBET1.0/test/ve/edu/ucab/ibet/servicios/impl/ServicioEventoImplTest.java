/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.edu.ucab.ibet.servicios.impl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
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
import ve.edu.ucab.ibet.dominio.Categoria;
import ve.edu.ucab.ibet.dominio.Evento;
import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.Politica;
import static org.junit.Assert.*;
import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioEvento;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioTableroGanancia;

/**
 *
 * @author jonathan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/config/hibernate/applicationContextTest.xml"})
public class ServicioEventoImplTest {

    public ServicioEventoImplTest() {
    }
    @Autowired
    private IServicioEvento servicioEvento;

    @Autowired
    private IServicioTableroGanancia servicioTableroGanancia;

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
     * Test of getGenericDao method, of class ServicioEventoImpl.
     */
//    @Test
    public void testGetGenericDao() {
        System.out.println("getGenericDao");
        ServicioEventoImpl instance = new ServicioEventoImpl();
        IGenericDao expResult = null;
        IGenericDao result = instance.getGenericDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGenericDao method, of class ServicioEventoImpl.
     */
//    @Test
    public void testSetGenericDao() {
        System.out.println("setGenericDao");
        IGenericDao genericDao = null;
        ServicioEventoImpl instance = new ServicioEventoImpl();
        instance.setGenericDao(genericDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHelperProp method, of class ServicioEventoImpl.
     */
//    @Test
    public void testGetHelperProp() {
        System.out.println("getHelperProp");
        ServicioEventoImpl instance = new ServicioEventoImpl();
        IHelperProperties expResult = null;
        IHelperProperties result = instance.getHelperProp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHelperProp method, of class ServicioEventoImpl.
     */
//    @Test
    public void testSetHelperProp() {
        System.out.println("setHelperProp");
        IHelperProperties helperProp = null;
        ServicioEventoImpl instance = new ServicioEventoImpl();
        instance.setHelperProp(helperProp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerEventosDeUnaCategoria method, of class ServicioEventoImpl.
     */
//    @Test
//    public void testObtenerEventosDeUnaCategoria() {
//        System.out.println("obtenerEventosDeUnaCategoria");
//        Integer categoria = 2;
//        List<Evento> result = servicioEvento.obtenerProximosEventosDeUnaCategoria(categoria);
//        for (Evento evento : result) {
//            System.out.println(evento.getNombre());
//        }
//        assertNotNull(result);
    // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    @Test
    public void testObtenerEventoporTableroGananciaTest() {
        System.out.println("Obtener Evento Tablero Ganancia");
        TableroGanancia tablero = new TableroGanancia(1, 1);
        Evento evento = null;
        evento = servicioEvento.obtenerEventoporTableroGanancia(tablero);
        assertNotNull(evento);
        System.out.println(evento.getHoraMaxima());
    }

//    @Test
    public void testObtenerProximosEventos() {
        System.out.println("Proximos eventos");
        List<Evento> proximosEventos = null;
        proximosEventos = servicioEvento.obtenerProximosEventos();
        assertNotNull(proximosEventos);
        for (Evento evento : proximosEventos) {
            System.out.println(evento.getNombre() + ":" +
                    evento.getFechaEvento() + ":" +
                    evento.getHora());
        }
    }

//    @Test
    public void testObtenerProximosEventosConImagen() {
        System.out.println("Proximos eventos");
        List<Evento> proximosEventos = null;
        proximosEventos = servicioEvento.obtenerProximosEventosConImagen();
        assertNotNull(proximosEventos);
        for (Evento evento : proximosEventos) {
            System.out.println(evento.getNombre() + ":" +
                    evento.getFechaEvento() + ":" +
                    evento.getHora());
        }
    }

//    @Test
    public void testObtenertableroGananciaPorEquiposYEvento() {
        System.out.println("Tablero de ganancia");
        String fechaEvento = "2009-11-28";
        Date fecha = UtilMethods.stringToFecha(fechaEvento);
        String nombreEquipo = "VillarrealA";
        TableroGanancia tablero = null;
        tablero = servicioEvento.obtenerTableroPorEquipoyEvento(fecha, nombreEquipo);
        assertNotNull(tablero);
        System.out.println(tablero.getParticipante().getNombre());
        System.out.println(tablero.getPropocionGano());
    }

    //@Test
    public void testFinalizarEvento() {
        System.out.println("finalizarEvento");
        String resultado = "ganador!";
        Integer idEvento = 9;
        Integer idParticipante = 18;
        Boolean gano = Boolean.TRUE;
        Boolean empato = Boolean.FALSE;
        try {
            servicioEvento.finalizarEvento(idEvento, resultado, idParticipante, gano, empato);
        } catch (ExcepcionNegocio en) {
            en.printStackTrace();
        }
        assertTrue(true);
    }
    //@Test
    public void testagregarEvento() {
        System.out.println("agregarEvento");
        String horaEvento = "02:45:00";
        String horaMax = "03:00:00";

        Participante part1 = new Participante(1);
        Participante part2 = new Participante(2);
        TableroGanancia t1 = new TableroGanancia();
        t1.setParticipante(part1);
        t1.setPropocionGano(4.0);
        t1.setProporcionEmpate(1.0);
        TableroGanancia t2 = new TableroGanancia();
        t2.setParticipante(part2);
        t2.setPropocionGano(2.0);
        t2.setProporcionEmpate(1.0);
        Categoria categoria = new Categoria(2);
        Politica politica = new Politica();
        politica.setMontoMaximo(4000.0);
        politica.setFinalizarAntes(Boolean.TRUE);
        Evento evento = new Evento();
        evento.setFechaEvento(new Date());
        evento.setFechaMaxima(new Date());
        evento.setHora(UtilMethods.stringToHora(horaEvento));
        evento.setHoraMaxima(UtilMethods.stringToHora(horaMax));
        evento.setIdCategoria(categoria);
        evento.setIdPolitica(politica);
        evento.setNombre("Nombre");
        evento.setResultado("");
        servicioEvento.agregarEvento(evento, t1, t2);
    }
    //@Test
    public void testEditarEvento() {
        System.out.println("editarEvento");
        String horaEvento = "02:45:00";
        String horaMax = "03:30:00";

        Participante part1 = new Participante(1);
        Participante part2 = new Participante(2);
        TableroGanancia t1 = new TableroGanancia();
        t1.setParticipante(part1);
        t1.setPropocionGano(5.0);
        t1.setProporcionEmpate(1.0);
        TableroGanancia t2 = new TableroGanancia();
        t2.setParticipante(part2);
        t2.setPropocionGano(2.0);
        t2.setProporcionEmpate(1.0);
        Categoria categoria = new Categoria(2);
        Politica politica = new Politica();
        politica.setMontoMaximo(4000.0);
        politica.setFinalizarAntes(Boolean.TRUE);
        Evento evento = new Evento(21);
        evento.setFechaEvento(new Date());
        evento.setFechaMaxima(new Date());
        evento.setHora(UtilMethods.stringToHora(horaEvento));
        evento.setHoraMaxima(UtilMethods.stringToHora(horaMax));
        evento.setIdCategoria(categoria);
        evento.setIdPolitica(politica);
        evento.setNombre("Evento");
        evento.setResultado("");
        servicioEvento.editarEvento(evento, t1, t2);
    }

    //@Test
    public void testActivarEvento() {
        System.out.println("Activar Evento");
        servicioEvento.activarEvento(21);
    }

    //@Test
    public void testDesactivarEvento() {
        System.out.println("Desactivar Evento");
        servicioEvento.desactivarEvento(21);
    }

    //@Test
    public void testListarEventos() {
        System.out.println("Listar Eventos");
        List<Evento> lista = servicioEvento.todosLosEventos();
        assertNotNull(lista);
        for (Evento evento : lista) {
            System.out.println(evento.getNombre());
        }
    }

    @Test
    public void testListarParticipantes() {
        System.out.println("Listar Participantes");
        List<Participante> lista = servicioTableroGanancia.obtenerParticipantesPorCategoria("Liga BBVA");
        assertNotNull(lista);
        for (Participante p : lista) {
            System.out.println(p.getNombre());
        }
    }
}
