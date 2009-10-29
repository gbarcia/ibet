/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pd.servicios.implementaciones;

import com.pd.dominio.Cliente;
import com.pd.generic.dao.interfaces.IGenericDao;
import com.pd.servicios.interfaces.IServicioCliente;
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
import static org.junit.Assert.*;

/**
 *
 * @author nath
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:web/WEB-INF/config/hibernate/applicationContextTest.xml"} )
public class ServicioClienteTest {
    @Autowired
    private IServicioCliente servicioCliente;
    public ServicioClienteTest() {
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
     * Test of getGenericDao method, of class ServicioCliente.
     */
    @Test
    public void testGetGenericDao() {
        System.out.println("getGenericDao");
        ServicioCliente instance = new ServicioCliente();
        IGenericDao expResult = null;
        IGenericDao result = instance.getGenericDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setGenericDao method, of class ServicioCliente.
     */
//    @Test
//    public void testSetGenericDao() {
//        System.out.println("setGenericDao");
//        IGenericDao genericDao = null;
//        ServicioCliente instance = new ServicioCliente();
//        instance.setGenericDao(genericDao);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of crearCliente method, of class ServicioCliente.
     */
    //@Test
    public void testCrearCliente() throws Exception {
        System.out.println("crearCliente");
         Cliente cliente = new Cliente();
         cliente.setNombre("Maya");
         cliente.setRif("J-800");
         cliente.setProductoCollection(null);
        servicioCliente.crearCliente(cliente);
        assertNotNull(servicioCliente);
    }

    /**
     * Test of actualizarCliente method, of class ServicioCliente.
     */
//    @Test
//    public void testActualizarCliente() throws Exception {
//        System.out.println("actualizarCliente");
//        Cliente cliente = null;
//        ServicioCliente instance = new ServicioCliente();
//        instance.actualizarCliente(cliente);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of listarClientes method, of class ServicioCliente.
     */
    @Test
    public void testListarClientes() throws Exception {
        System.out.println("listarClientes");
        List<Cliente> result = servicioCliente.listarClientes();
        assertNotNull(result);
        for (Cliente c : result) {
            System.out.println(c.getNombre());
        }
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}