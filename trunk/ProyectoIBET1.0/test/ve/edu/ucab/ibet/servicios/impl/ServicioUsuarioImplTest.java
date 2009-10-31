/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:web/WEB-INF/config/hibernate/applicationContextTest.xml"})
public class ServicioUsuarioImplTest {

    public ServicioUsuarioImplTest() {
    }
    @Autowired
    private IServicioUsuario servicioUsuario;
    /**
     * Test of registroNuevoUsuarioM method, of class ServicioUsuarioImpl.
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
        user.setCorreo("trujillo.jonathan@gmail.com");
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

}