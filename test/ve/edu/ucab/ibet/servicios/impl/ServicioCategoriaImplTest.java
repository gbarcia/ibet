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
import ve.edu.ucab.ibet.dominio.Categoria;
import static org.junit.Assert.*;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioCategoria;

/**
 * Clase de pruebas unitarias de Categorias y sus validaciones 
 * @author jonathan
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/config/hibernate/applicationContextTest.xml"})
public class ServicioCategoriaImplTest {

    public ServicioCategoriaImplTest() {
    }
    @Autowired
    private IServicioCategoria servicioCategoria;

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
     * Test of obtenerCategorias method, of class ServicioCategoriaImpl.
     */
    //@Test
    public void testObtenerCategorias() {
        System.out.println("obtenerCategorias");
        List<Categoria> result = servicioCategoria.obtenerCategoriasPadres();
        for (Categoria categoria : result) {
            System.out.println(categoria.getNombre());
        }
        assertNotNull(result);
    }

    /**
     * Prueba para obtener las subcategorias de una categoria 
     */
    //@Test
    public void testObtenerSubcategoriasDeUnaCategoria() {
        System.out.println("obetenerSubcategoriasDeUnaCategoria");
        Categoria categoria = new Categoria(1);
        List<Categoria> result = servicioCategoria.obtenerSubcategoriasDeUnaCategoria(categoria);
        for (Categoria categoria1 : result) {
            System.out.println(categoria1.getNombre());
        }
        assertNotNull(result);
    }

    /**
     * Prueba para listar categorias 
     */
    //@Test
    public void testListarCategorias() {
        System.out.println("Listar Categorias");
        List<Categoria> result = servicioCategoria.listarCategorias();
        for (Categoria categoria1 : result) {
            System.out.println(categoria1.getNombre());
        }
        assertNotNull(result);
    }

    /**
     * Prueba para agregar una nueva categoria 
     */
    //@Test
    public void testagregarCategoria() {
        System.out.println("Agregar Categoria");
        Categoria categoria = new Categoria();
        categoria.setEmpate(true);
        categoria.setHabilitada(true);
        categoria.setIdCategoria(null);
        categoria.setLogicaAutomatica(false);
        categoria.setNombre("Politica");
        categoria.setNombreLogica(null);
        servicioCategoria.agregarCategoria(categoria);
    }

    /**
     * Prueba para obtener una categora por su id 
     */
    //@Test
    public void testObtenerCategoria() {
        System.out.println("obtener Categoria");
        Categoria categoria = servicioCategoria.obtenerCategoria(1);
        System.out.println(categoria.getNombre());
    }

    /**
     * Prueba apra editar una categoria por su id 
     */
    //@Test
    public void testEditarCategoria() {
        System.out.println("editar Categoria");
        Categoria categoria = servicioCategoria.obtenerCategoria(1);
        categoria.setNombre("FURBOE");
        servicioCategoria.editarCategotia(categoria);
    }

    /**
     * Prueba para inhabilitar una categoria por su id 
     */
    @Test
    public void testInhabilitarCategoria (){
        System.out.println("Inhabilitar categoria");
        servicioCategoria.inhabilitarCategoria(1);
    }
}
