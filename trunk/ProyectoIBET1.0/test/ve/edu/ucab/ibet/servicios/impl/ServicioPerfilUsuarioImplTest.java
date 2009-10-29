/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.servicios.impl;

import java.util.Date;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioPerfilUsuario;

/**
 *
 * @author maya
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:web/WEB-INF/config/hibernate/applicationContextTest.xml"})
public class ServicioPerfilUsuarioImplTest {
    @Autowired
    private IServicioPerfilUsuario servicioPerfilUsuario;

    public ServicioPerfilUsuarioImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getHp method, of class ServicioPerfilUsuarioImpl.
     */
//    @Test
    public void testGetHp() {
        System.out.println("getHp");
        ServicioPerfilUsuarioImpl instance = new ServicioPerfilUsuarioImpl();
        IHelperProperties expResult = null;
        IHelperProperties result = instance.getHp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHp method, of class ServicioPerfilUsuarioImpl.
     */
//    @Test
    public void testSetHp() {
        System.out.println("setHp");
        IHelperProperties hp = null;
        ServicioPerfilUsuarioImpl instance = new ServicioPerfilUsuarioImpl();
        instance.setHp(hp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGenericDao method, of class ServicioPerfilUsuarioImpl.
     */
//    @Test
    public void testGetGenericDao() {
        System.out.println("getGenericDao");
        ServicioPerfilUsuarioImpl instance = new ServicioPerfilUsuarioImpl();
        IGenericDao expResult = null;
        IGenericDao result = instance.getGenericDao();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGenericDao method, of class ServicioPerfilUsuarioImpl.
     */
//    @Test
    public void testSetGenericDao() {
        System.out.println("setGenericDao");
        IGenericDao genericDao = null;
        ServicioPerfilUsuarioImpl instance = new ServicioPerfilUsuarioImpl();
        instance.setGenericDao(genericDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerDatosUsuarioM method, of class ServicioPerfilUsuarioImpl.
     */
//    @Test
    public void testObtenerDatosUsuarioM() throws Exception {
        System.out.println("obtenerDatosUsuarioM");
        String username = "maya";
        Users result = servicioPerfilUsuario.obtenerDatosUsuarioM(username);
        assertNotNull(result);
        System.out.println(result.getApellido());
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Test
    public void testActualizarDatosUsuarioM() throws Exception {
        System.out.println("actualizarDatosUsuarioM");
        Users user = new Users();
        user.setUsername("maya");
        user.setNombre("maria");
        user.setApellido("uribe");
        user.setPassword("12345");
        user.setCorreo("mayita.uribe@gmail.com");
        user.setSexo("f");
        user.setPais("venezuela");
        user.setCiudad("caracas");
        user.setEstado("miranda");
        user.setCalle("cafetal");
        user.setConfirmado(true);
        user.setEnabled(true);
        user.setCodigoPostal(1010);
        user.setFechaNacimiento(new Date());
        user.setTelefono("04169513436");
        servicioPerfilUsuario.actualizarDatosUsuarioM(user);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

}