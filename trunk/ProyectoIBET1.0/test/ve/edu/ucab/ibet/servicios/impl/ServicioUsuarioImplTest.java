package ve.edu.ucab.ibet.servicios.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 * pruebas unitarias para servicio usuario
 * @author Gerardo Barcia
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/config/hibernate/applicationContextTest.xml"})
public class ServicioUsuarioImplTest {

    public ServicioUsuarioImplTest() {
    }
    @Autowired
    private IServicioUsuario servicioUsuario;

    /**
     * Prueba Unitaria registro usuario
     */
    @Test
    public void testRegistroNuevoUsuarioM() throws Exception {
        System.out.println("registroNuevoUsuarioM");
        Users user = new Users();
        user.setApellido("Castellano");
        user.setApuestaCollection(null);
        user.setCalle("Av. Avila");
        user.setCiudad("Caracas");
        user.setCodigoPostal(1070);
        user.setCorreo("gerardobarcia@gmail.com");
        user.setEstado("Miranda");
        user.setFechaNacimiento(new Date());
        user.setNombre("Kate");
        user.setPais("Venezuela");
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        user.setPassword(md5.encodePassword("1234", null));
        user.setSexo("f");
        user.setTelefono("2345");
        user.setUsername("kate");
        boolean expResult = true;
        boolean result = servicioUsuario.registroNuevoUsuarioM(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }
//    Prueba unitaria existe usuario
    //@Test
//
//    public void testExisteUsuarioM() throws Exception {
//        System.out.println("existeUsuario");
//        Users user = new Users();
//        user.setApellido("Castellano");
//        user.setApuestaCollection(null);
//        user.setCalle("Av. Avila");
//        user.setCiudad("Caracas");
//        user.setCodigoPostal(1070);
//        user.setCorreo("gerardobarcia@gmail.com");
//        user.setEstado("Miranda");
//        user.setFechaNacimiento(new Date());
//        user.setNombre("Kate");
//        user.setPais("Venezuela");
//        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//        user.setPassword(md5.encodePassword("1234", null));
//        user.setSexo("f");
//        user.setTelefono("2345");
//        user.setUsername("kate");
//        boolean expResult = false;
//        boolean result = servicioUsuario.existeUsuario(user);
//        assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    // fail("The test case is a prototype.");
//    }

    /**
     * Test of obtenerDatosUsuarioM method, of class ServicioPerfilUsuarioImpl.
     */
//    @Test
    public void testObtenerDatosUsuarioM() throws Exception {
        System.out.println("obtenerDatosUsuarioM");
        String username = "maya";
        Users result = servicioUsuario.obtenerDatosUsuarioM(username);
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
        servicioUsuario.actualizarDatosUsuarioM(user);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
