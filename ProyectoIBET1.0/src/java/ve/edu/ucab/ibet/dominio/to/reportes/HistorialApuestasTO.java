/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.dominio.to.reportes;

import java.util.Date;

/**
 *
 * @author maya
 */
public class HistorialApuestasTO {

    private String username;
    private Date fecha;
    private double monto;
    private String eventoNombre;
    private Date eventoFecha;
    private String eventoResultado;
    private String participanteNombre;
    
    public HistorialApuestasTO() {
    }

    public Date getEventoFecha() {
        return eventoFecha;
    }

    public void setEventoFecha(Date eventoFecha) {
        this.eventoFecha = eventoFecha;
    }

    public String getEventoNombre() {
        return eventoNombre;
    }

    public void setEventoNombre(String eventoNombre) {
        this.eventoNombre = eventoNombre;
    }

    public String getEventoResultado() {
        return eventoResultado;
    }

    public void setEventoResultado(String eventoResultado) {
        this.eventoResultado = eventoResultado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getParticipanteNombre() {
        return participanteNombre;
    }

    public void setParticipanteNombre(String participanteNombre) {
        this.participanteNombre = participanteNombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
