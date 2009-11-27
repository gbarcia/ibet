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
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
