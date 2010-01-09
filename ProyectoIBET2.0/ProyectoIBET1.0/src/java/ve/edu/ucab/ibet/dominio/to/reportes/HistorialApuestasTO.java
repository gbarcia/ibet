package ve.edu.ucab.ibet.dominio.to.reportes;

import java.util.Date;

/**
 * Transfer object para manejar el historial de apuestas 
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
    private Boolean ganador;
    
    public HistorialApuestasTO() {
    }

    public HistorialApuestasTO(String username, Date fecha, double monto, String eventoNombre, Date eventoFecha, String eventoResultado, String participanteNombre, Boolean ganadorP) {
        this.username = username;
        this.fecha = fecha;
        this.monto = monto;
        this.eventoNombre = eventoNombre;
        this.eventoFecha = eventoFecha;
        this.eventoResultado = eventoResultado;
        this.participanteNombre = participanteNombre;
        this.ganador = ganadorP;
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

    public Boolean getGanador() {
        return ganador;
    }

    public void setGanador(Boolean ganador) {
        this.ganador = ganador;
    }
    
}
