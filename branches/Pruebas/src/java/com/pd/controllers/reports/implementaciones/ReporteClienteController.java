/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pd.controllers.reports.implementaciones;

import com.pd.controllers.reports.interfaces.ReporteGenerator;
import com.pd.generic.excepciones.GeneralException;
import com.pd.servicios.interfaces.IServicioCliente;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 *
 * @author nath
 */
public class ReporteClienteController extends MultiActionController implements ReporteGenerator {

    private final String NOMRE_REPORTE_PDF= "pruebaReporte";
    private final String NOMBRE_REPORTE_EXCEL = "pruebaReporteXLS";

    IServicioCliente servicioCliente;

    public IServicioCliente getServicioCliente() {
        return servicioCliente;
    }

    public void setServicioCliente(IServicioCliente servicioCliente) {
        this.servicioCliente = servicioCliente;
    }

    public ModelAndView generarReportePDF(HttpServletRequest request, HttpServletResponse response)
            throws GeneralException {
        return new ModelAndView(NOMRE_REPORTE_PDF, getModel());
    }

    public ModelAndView generarReporteXLS(HttpServletRequest request, HttpServletResponse response) throws GeneralException {
        return new ModelAndView(NOMBRE_REPORTE_EXCEL, getModel());
    }

    public Map getModel() throws GeneralException {
        Map model = new HashMap();
        model.put("titulo", "Titulo");
        model.put("dataSource", getData());
        model.put("fecha", new Date().toString());
        return model;
    }

    public List getData() throws GeneralException {
        List lista = new ArrayList();
        lista.addAll(servicioCliente.listarClientes());
        return lista;
    }

}
