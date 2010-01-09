package ve.edu.ucab.ibet.dominio.to.forms;

import java.io.Serializable;
import java.util.Date;
import ve.edu.ucab.ibet.dominio.enums.TipoDocumentoReporte;

/**
 * Transfer object para manejar las fechas y tipo de documento de los reportes 
 * @author maya
 */
public class FechasTO implements Serializable{

    private Date fechaInicio;
    private Date fechaFin;
    private TipoDocumentoReporte tipoReporte;

    public FechasTO() {
    }

    public FechasTO(Date fechaInicio, Date fechaFin, TipoDocumentoReporte tipoReporte) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoReporte = tipoReporte;
    }

    public FechasTO(TipoDocumentoReporte tipoReporte) {
        this.tipoReporte = tipoReporte;
    }
    
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public TipoDocumentoReporte getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(TipoDocumentoReporte tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

}
