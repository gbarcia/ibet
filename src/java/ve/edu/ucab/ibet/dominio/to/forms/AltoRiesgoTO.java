package ve.edu.ucab.ibet.dominio.to.forms;

import ve.edu.ucab.ibet.dominio.enums.TipoDocumentoReporte;

/**
 * Transfer object para manejar los eventos de alto riesgo, el monto y tipo de documento de los reportes 
 * @author maya
 */
public class AltoRiesgoTO {

    private Double monto;
    private TipoDocumentoReporte tipoReporte;

    public AltoRiesgoTO() {
    }

    public AltoRiesgoTO(Double monto, TipoDocumentoReporte tipoReporte) {
        this.monto = monto;
        this.tipoReporte = tipoReporte;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public TipoDocumentoReporte getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(TipoDocumentoReporte tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

}
