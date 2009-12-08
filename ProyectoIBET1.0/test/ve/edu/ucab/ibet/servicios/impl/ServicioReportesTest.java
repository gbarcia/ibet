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
import ve.edu.ucab.ibet.dominio.Categoria;
import ve.edu.ucab.ibet.dominio.to.reportes.CantidadUsuariosCategoriaTO;
import ve.edu.ucab.ibet.dominio.to.reportes.CategoriasGananciaPerdidaTO;
import ve.edu.ucab.ibet.dominio.to.reportes.EventosAltoRiesgoTO;
import ve.edu.ucab.ibet.dominio.to.reportes.HistorialApuestasTO;
import ve.edu.ucab.ibet.dominio.to.reportes.UsuariosMayorAciertosTO;
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
        Date fechaFin = UtilMethods.stringToFecha("2009-11-28");

        System.out.println("fecha inicio: " + fechaInicio);

        List<HistorialApuestasTO> result = servicioReporte.reporteHistorialApuestas(username, fechaInicio, fechaFin);
        String fecha;
        for (HistorialApuestasTO r : result) {
            if (r.getFecha() == null) {
                fecha = "null";
            } else {
                fecha = UtilMethods.fechaToString(r.getFecha());
            }
            System.out.println(r.getUsername() + ", " + r.getEventoFecha() + ", " + fecha + "\n ");
        }
        assertNotNull(result);
    }

    @Test
    public void testReporteCategoriasGanancias() throws Exception {
        System.out.println("reporteCategoriasGanancias");
        Date fechaInicio = UtilMethods.stringToFecha("2009-10-01");
        Date fechaFin = UtilMethods.stringToFecha("2009-12-25");
        List<CategoriasGananciaPerdidaTO> result = servicioReporte.reporteCategoriasGanancias(fechaInicio, fechaFin);
        for (CategoriasGananciaPerdidaTO r : result) {
            System.out.println(r.getNombreCategoria() + ", " + r.getMontoTotal() + "\n ");
        }
        System.out.println("");
        assertNotNull(result);
    }

    @Test
    public void testReporteCategoriasPerdidas() throws Exception {
        System.out.println("reporteCategoriasPerdidas");
        Date fechaInicio = UtilMethods.stringToFecha("2009-11-01");
        Date fechaFin = UtilMethods.stringToFecha("2009-11-25");
        List<CategoriasGananciaPerdidaTO> result = servicioReporte.reporteCategoriasPerdidas(fechaInicio, fechaFin);
        for (CategoriasGananciaPerdidaTO r : result) {
            System.out.println(r.getNombreCategoria() + ", " + r.getMontoTotal());
        }
        System.out.println("");
        assertNotNull(result);
    }

//    @Test
    public void testListarCategorias() throws Exception {
        System.out.println("listarCategorias");

        List<Categoria> result = servicioReporte.listarCategorias();
        for (Categoria r : result) {
            System.out.println(r.getId() + ", " + r.getNombre());
        }
        System.out.println("");
        assertNotNull(result);
    }

//    @Test
    public void testCantidadUsuariosCategoria() throws Exception {
        System.out.println("cantidadUsuariosCategoria");

        List<CantidadUsuariosCategoriaTO> result = servicioReporte.reporteCantidadUsuariosCategoria();
        Long cantidad;
        for (CantidadUsuariosCategoriaTO r : result) {
            cantidad = r.getCantidadUsuarios();
            System.out.println(r.getNombreCategoria() + ", " + cantidad);
        }
        assertNotNull(result);
    }

//    @Test
    public void testEventosAltoRiesgo() throws Exception {
        System.out.println("eventosAltoRiesgo");

        Double monto = 600.0;
        List<EventosAltoRiesgoTO> result = servicioReporte.reporteEventosAltoRiesgo(monto);

        for (EventosAltoRiesgoTO r : result) {
            System.out.println(r.getEvento() + ", " + r.getMonto());
        }
        assertNotNull(result);
    }

//    @Test
    public void testUsuariosMayorAciertos() throws Exception {
        System.out.println("usuariosMayorAciertos");

        List<UsuariosMayorAciertosTO> result = servicioReporte.reporteUsuariosMayorAciertos();

        for (UsuariosMayorAciertosTO r : result) {
            System.out.println(r.getUsername() + ", " + r.getCantidadAciertos());
        }
        assertNotNull(result);
    }
}
