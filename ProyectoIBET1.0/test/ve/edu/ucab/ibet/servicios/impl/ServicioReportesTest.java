/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.servicios.impl;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ve.edu.ucab.ibet.dominio.to.reportes.CategoriasGananciasTO;
import ve.edu.ucab.ibet.dominio.to.reportes.HistorialApuestasTO;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioReportes;
import static org.junit.Assert.*;

/**
 *
 * @author maya
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/config/hibernate/applicationContextTest.xml"})
public class ServicioReportesTest {

    public ServicioReportesTest() {
    }

    @Autowired
    private IServicioReportes servicioReporte;

    /**
     * Test of reporteHistorialApuestas method, of class ServicioReporteHistorialApuestas.
     */
//    @Test
    public void testReporteHistorialApuestas() throws Exception {
        System.out.println("reporteHistorialApuestas");
        String username = "maya";
        Date fechaInicio = UtilMethods.stringToFecha("2009-11-01");
        Date fechaFin = UtilMethods.stringToFecha("2009-11-25");

        System.out.println("fecha inicio: " + fechaInicio);
       
        List<HistorialApuestasTO> result = servicioReporte.reporteHistorialApuestas(username, fechaInicio, fechaFin);
        for (HistorialApuestasTO r : result) {
            System.out.println(r.getUsername() + ", " + r.getEventoFecha() + ", " + r.getFecha() +"\n ");
        }
        assertNotNull(result);
    }

    @Test
    public void testReporteCategoriasGanancias() throws Exception {
        System.out.println("reporteCategoriasGanancias");

        List<CategoriasGananciasTO> result = servicioReporte.reporteCategoriasGanancias();
        for (CategoriasGananciasTO r : result) {
            System.out.println(r.getNombreCategoria() + ", " + r.getMontoTotal() + "\n ");
        }
        assertNotNull(result);
    }

}