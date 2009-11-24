/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.dominio.to.forms;

import ve.edu.ucab.ibet.dominio.enums.TipoDocumentoReporte;

/**
 *
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
