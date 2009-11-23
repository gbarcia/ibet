/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.dominio.to.reportes;

/**
 *
 * @author maya
 */
public class EventosAltoRiesgoTO {

    private Double monto;
    private String evento;

    public EventosAltoRiesgoTO() {
    }

    public EventosAltoRiesgoTO(Double monto, String evento) {
        this.monto = monto;
        this.evento = evento;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

}
