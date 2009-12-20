package ve.edu.ucab.ibet.dominio.to.reportes;

import java.io.Serializable;

/**
 * Clase para almacenar informacion sobre el detalle de un reporte
 * @author Gerardo Barcia
 * @version 1.0
 */
public class DetalleReporteTO implements Serializable {

    private String nombreReporte;
    private String tipoReporte;
    private String url;

    public DetalleReporteTO() {
    }

    public DetalleReporteTO(String nombreReporte, String tipoReporte) {
        this.nombreReporte = nombreReporte;
        this.tipoReporte = tipoReporte;
    }

    public DetalleReporteTO(String nombreReporte, String tipoReporte, String url) {
        this.nombreReporte = nombreReporte;
        this.tipoReporte = tipoReporte;
        this.url = url;
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
