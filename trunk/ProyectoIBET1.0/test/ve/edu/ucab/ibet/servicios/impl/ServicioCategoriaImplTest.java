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
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioCategoria;

/**
 *
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
     * Test of getGenericDao method, of class ServicioCategoriaImpl.
     */
    //@Test
    public void testGetGenericDao() {
        System.out.println("getGenericDao");
        ServicioCategoriaImpl instance = new ServicioCategoriaImpl();
        IGenericDao expResult = null;
        IGenericDao result = instance.getGenericDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGenericDao method, of class ServicioCategoriaImpl.
     */
    //@Test
    public void testSetGenericDao() {
        System.out.println("setGenericDao");
        IGenericDao genericDao = null;
        ServicioCategoriaImpl instance = new ServicioCategoriaImpl();
        instance.setGenericDao(genericDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHelperProp method, of class ServicioCategoriaImpl.
     */
    //@Test
    public void testGetHelperProp() {
        System.out.println("getHelperProp");
        ServicioCategoriaImpl instance = new ServicioCategoriaImpl();
        IHelperProperties expResult = null;
        IHelperProperties result = instance.getHelperProp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHelperProp method, of class ServicioCategoriaImpl.
     */
    //@Test
    public void testSetHelperProp() {
        System.out.println("setHelperProp");
        IHelperProperties helperProp = null;
        ServicioCategoriaImpl instance = new ServicioCategoriaImpl();
        instance.setHelperProp(helperProp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
//       fail("The test case is a prototype.");
    }

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

    @Test
    public void testListarCategorias() {
        System.out.println("Listar Categorias");
        List<Categoria> result = servicioCategoria.listarCategorias();
        for (Categoria categoria1 : result) {
            System.out.println(categoria1.getNombre());
        }
        assertNotNull(result);
    }
}
