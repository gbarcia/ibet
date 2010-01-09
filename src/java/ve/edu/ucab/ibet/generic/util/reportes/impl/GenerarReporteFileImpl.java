package ve.edu.ucab.ibet.generic.util.reportes.impl;

import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import ve.edu.ucab.ibet.dominio.enums.TipoDocumentoReporte;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.generic.util.reportes.interfaces.IGenerarReporteFile;

/**
 * Implementacion para generar el reporte en excel o pdf que luego sera
 * guardado en disco
 * @author Gerardo Barcia
 * @version 1.0
 */
public class GenerarReporteFileImpl implements IGenerarReporteFile {

    private JasperPrint jasperPrint;
    private IHelperProperties helperProperties;
    private static String NOMBRE_JASPER = "tarifa";

    public IHelperProperties getHelperProperties() {
        return helperProperties;
    }

    public void setHelperProperties(IHelperProperties helperProperties) {
        this.helperProperties = helperProperties;
    }

    public void generarReporte(Map<String, Object> parameters, TipoDocumentoReporte tipo,
            String nombreReporte) {
        switch (tipo) {
            case PDF:
                try {
                    String urlJasper = helperProperties.getString("reportes.directorio.jasper") +
                            NOMBRE_JASPER + helperProperties.getString("reportes.extencion.jasper");

                    String urlDestino = helperProperties.getString("reportes.directorio.correo") +
                            nombreReporte + helperProperties.getString("reportes.extencion.pdf");

                    jasperPrint = JasperFillManager.fillReport(urlJasper, parameters);
                    JasperExportManager.exportReportToPdfFile(jasperPrint, urlDestino);
                    break;
                } catch (JRException ex) {
                    ex.printStackTrace();
                }

            case EXCEL:
                String urlJasper = helperProperties.getString("reportes.directorio.jasper") +
                        NOMBRE_JASPER + helperProperties.getString("reportes.extencion.jasper");

                String urlDestino = helperProperties.getString("reportes.directorio.correo") +
                        nombreReporte + helperProperties.getString("reportes.extencion.xls");
                try {
                    jasperPrint = JasperFillManager.fillReport(urlJasper, parameters);
                    JasperExportManager.exportReportToPdfFile(jasperPrint, urlDestino);
                    break;
                } catch (JRException ex) {
                    ex.printStackTrace();
                }
        }
    }
}
