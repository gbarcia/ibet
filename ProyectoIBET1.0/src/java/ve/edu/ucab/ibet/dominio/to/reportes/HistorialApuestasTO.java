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
    private Date apuestaFecha;
    private double apuestaMonto;
    private String eventoNombre;
    private Date eventoFecha;
    private String eventoResultado;
    private String participanteNombre;
    
    public HistorialApuestasTO() {
    }

    public Date getApuestaFecha() {
        return apuestaFecha;
    }

    public void setApuestaFecha(Date apuestaFecha) {
        this.apuestaFecha = apuestaFecha;
    }

    public double getApuestaMonto() {
        return apuestaMonto;
    }

    public void setApuestaMonto(double apuestaMonto) {
        this.apuestaMonto = apuestaMonto;
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
