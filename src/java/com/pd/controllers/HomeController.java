/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pd.controllers;

import com.pd.dominio.Cliente;
import com.pd.dominio.enums.TipoDocumentoReporte;
import com.pd.generic.excepciones.GeneralException;
import com.pd.generic.util.GenerarReporteDisco;
import com.pd.generic.util.HelperProperties.HelperProperties;
import com.pd.generic.util.mail.interfaces.IMailService;
import com.pd.servicios.interfaces.IServicioCliente;
import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
/**
 *
 * @author nath
 */
public class HomeController implements Controller {

    private GenerarReporteDisco generarReporteDisco;

    private IServicioCliente servicioCliente;

    public IServicioCliente getServicioCliente() {
        return servicioCliente;
    }

    public void setServicioCliente(IServicioCliente servicioCliente) {
        this.servicioCliente = servicioCliente;
    }

    public GenerarReporteDisco getGenerarReporteDisco() {
        return generarReporteDisco;
    }

    public void setGenerarReporteDisco(GenerarReporteDisco generarReporteDisco) {
        this.generarReporteDisco = generarReporteDisco;
    }

    private IMailService servicioMail;

    private HttpSession session;

    public IMailService getServicioMail() {
        return servicioMail;
    }

    public void setServicioMail(IMailService servicioMail) {
        this.servicioMail = servicioMail;
    }

    public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1)
            throws ServletException,IOException, GeneralException {



        

        try { // Call Web Service Operation
            _211._22._168._192._1234.Ubet service = new _211._22._168._192._1234.Ubet();
            _211._22._168._192._1234.UbetSoap port = service.getUbetSoap12();
            // TODO initialize WS operation arguments here
            java.lang.String fecha = "01/12/2009";
            java.lang.String equipo1 = "Leones del Caracas";
            java.lang.String equipo2 = "Navegantes del Magallanes";
            // TODO process result here
            _211._22._168._192._1234.Respuesta result = port.consultarProporcionEvento(fecha, equipo1, equipo2);
            if (result != null) {
            System.out.println("Result = "+result.getParticipante1());
            System.out.println(result.getParticipante2());
            System.out.println(result.getProporcion1());
            System.out.println(result.getProporcion2());
            }
            else {
                System.out.println("vino nulo");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }



//        try { // Call Web Service Operation
//            ve.edu.ucab.ibet.servicios.impl.ws.IbetWebServices_Service service = new ve.edu.ucab.ibet.servicios.impl.ws.IbetWebServices_Service();
//            ve.edu.ucab.ibet.servicios.impl.ws.IbetWebServices port = service.getIbetWebServicesPort();
//            // TODO initialize WS operation arguments here
//            java.lang.String fecha = "28/11/2009";
//            java.lang.String equipoUno = "Villarreal";
//            java.lang.String equipoDos = "Sporting Gijon";
//            // TODO process result here
//            ve.edu.ucab.ibet.servicios.impl.ws.RespuestaProporcionWS result = port.consultarProporcionEvento(fecha, equipoUno, equipoDos);
//            System.out.println("Result = "+result.getEquipoUno());
//            System.out.println(result.getProporcionEquipoUno());
//        } catch (Exception ex) {
//           ex.printStackTrace();
//        }
//
//
//        try { // Call Web Service Operation
//            ve.edu.ucab.ibet.servicios.impl.ws.IbetWebServices_Service service = new ve.edu.ucab.ibet.servicios.impl.ws.IbetWebServices_Service();
//            ve.edu.ucab.ibet.servicios.impl.ws.IbetWebServices port = service.getIbetWebServicesPort();
//            // TODO initialize WS operation arguments here
//            java.lang.String fecha = "28/11/2009";
//            java.lang.String participanteUno = "Villarreal";
//            java.lang.String participanteDos = "Sporting Gijon";
//            // TODO process result here
//            java.lang.String result = port.consultarResultadoEvento(fecha, participanteUno, participanteDos);
//            System.out.println("Result = "+result);
//        } catch (Exception ex) {
//            // TODO handle custom exceptions here
//        }


        Principal p = arg0.getUserPrincipal();
        System.out.println(p.getName());

        List<String> lista = new ArrayList<String>();
       File archivo = new File("/home/nath/NetBeansProjects/EnvioMail/web/WEB-INF/web.xml");
            HelperProperties hp = new HelperProperties();
            String titulo = "Sr.";
            String nombre= "Raul";
            lista.add(titulo);
            lista.add(nombre);
            lista.add("usuario");
            lista.add("1234j");
            String asunto = hp.getString("correos.plantillas.asunto.registro");
            String cuerpo;
            cuerpo = ("<p>" + hp.getString("correos.plantillas.cuerpo.encabezado", lista) + "</p>");
            cuerpo += ("<p>" + hp.getString("correos.plantillas.cuerpo.mensaje.linea1") + "</p>");
            cuerpo += ("<p>" + hp.getString("correos.plantillas.cuerpo.mensaje.linea2") + "</p>");
            cuerpo += ("<p>" + hp.getString("correos.plantillas.cuerpo.mensaje.linea3") + "</p>");
            cuerpo += ("<p>" + hp.getString("correos.plantillas.cuerpo.mensaje.linea4",lista) + "</p>");
            cuerpo += ("<p>" + hp.getString("correos.plantillas.cuerpo.mensaje.linea5",lista) + "</p>");
            cuerpo += ("<p>" + hp.getString("correos.plantillas.cuerpo.mensaje.linea6") + "</p>");
            cuerpo += ("<p>" + hp.getString("correos.plantillas.cuerpo.mensaje.linea7") + "</p>");
            cuerpo += ("<p>" + hp.getString("correos.plantillas.cuerpo.mensaje.linea8") + "</p>");

            //servicioMail.send("gerardobarciap@gmail.com", asunto, cuerpo);


            session = arg0.getSession(true);
            session.setAttribute("nombre", "El nombre");
           String now = (new Date().toString());
           List<Cliente> listaClientes = servicioCliente.listarClientes();

           // Reportes internos

           
//           Map<String,Object> parameters = new HashMap();
//           parameters.put("nombre", "Gerardo");
//
//           generarReporteDisco.generarReporte(parameters, TipoDocumentoReporte.PDF, "tarifa");
           return new ModelAndView("home","listaClientes",listaClientes);
    }

}
