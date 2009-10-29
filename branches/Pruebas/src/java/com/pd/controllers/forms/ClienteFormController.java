/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pd.controllers.forms;

import com.pd.dominio.Cliente;
import com.pd.dominio.Estado;
import com.pd.dominio.Producto;
import com.pd.dominio.forms.ClienteForm;
import com.pd.servicios.interfaces.IServicioCliente;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.concurrent.SessionIdentifierAware;
import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author nath
 */
public class ClienteFormController extends SimpleFormController{

    private IServicioCliente servicioCliente;

    public IServicioCliente getServicioCliente() {
        return servicioCliente;
    }

    public void setServicioCliente(IServicioCliente servicioCliente) {
        this.servicioCliente = servicioCliente;
    }

    private static final String[] ALL_STATES = {
    "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL",
    "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME",
    "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
    "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI",
    "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WV", "WI",
    "WY"
  };

    public ClienteFormController() {
        setCommandClass(ClienteForm.class);
        setCommandName("cliente");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request)
      throws Exception {
      HttpSession session = request.getSession(true);
      ClienteForm clienteForm = (ClienteForm) super.formBackingObject(request);
      clienteForm.setRif((String) session.getAttribute("nombre"));
      clienteForm.setNombre(new String());
      return clienteForm;
  }


    @Override
    protected Map referenceData(HttpServletRequest request) throws Exception {
            Map referenceData = new HashMap();
            referenceData.put("estados", ALL_STATES);
            referenceData.put("esta", Estado.values());
            return referenceData;
    }

    @Override
    protected ModelAndView onSubmit(Object command, BindException errors) throws Exception {
        ClienteForm cliente = (ClienteForm) command;
       // servicioCliente.crearCliente(cliente);
        if (errors.hasErrors())
        System.out.println("mal");
        else System.out.println("bien");
        System.out.println(cliente.getEstado());
        MultipartFile file = cliente.getFile();
        System.out.println(file.getOriginalFilename());
        File f = new File("/home/nath/NetBeansProjects/PruebaParaDesarrollo/web/WEB-INF/reportes/"+ file.getOriginalFilename());
        file.transferTo(f);
        return new ModelAndView(new RedirectView(getSuccessView()));
    }
}
