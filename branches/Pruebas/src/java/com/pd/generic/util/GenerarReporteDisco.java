/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.generic.util;

import com.pd.dominio.enums.TipoDocumentoReporte;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author nath
 */
public class GenerarReporteDisco {

    private JasperPrint jasperPrint;

    public void generarReporte(Map<String, Object> parameters, TipoDocumentoReporte
            tipo, String nombreReporte) {

        switch (tipo) {
            case PDF:
                try {
                    ResourceBundle rb = ResourceBundle.getBundle("com.pd.conf.conf");
                    String urlJasper = rb.getString("reportes.directorio.jasper") +
                            nombreReporte + rb.getString("reportes.extencion.jasper");

                    String urlDestino = rb.getString("reportes.directorio.correo") +
                            nombreReporte + rb.getString("reportes.extencion.pdf");

                    jasperPrint = JasperFillManager.fillReport(urlJasper, parameters);
                    JasperExportManager.exportReportToPdfFile(jasperPrint,urlDestino);
                } catch (JRException ex) {
                    Logger.getLogger(GenerarReporteDisco.class.getName()).log(Level.SEVERE, null, ex);
                }

            case EXCEL:
                ResourceBundle rb = ResourceBundle.getBundle("com.pd.conf.conf");
                    String urlJasper = rb.getString("reportes.directorio.jasper") +
                            nombreReporte + rb.getString("reportes.extencion.jasper");

                    String urlDestino = rb.getString("reportes.directorio.correo") +
                            nombreReporte + rb.getString("reportes.extencion.xls");
        try {
            jasperPrint = JasperFillManager.fillReport(urlJasper, parameters);
            JasperExportManager.exportReportToPdfFile(jasperPrint,urlDestino);
        } catch (JRException ex) {
            Logger.getLogger(GenerarReporteDisco.class.getName()).log(Level.SEVERE, null, ex);
        }

        }

    }
}
