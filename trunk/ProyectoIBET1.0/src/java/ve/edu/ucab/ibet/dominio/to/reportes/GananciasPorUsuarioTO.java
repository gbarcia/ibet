package ve.edu.ucab.ibet.dominio.to.reportes;

import java.util.Date;

/**
 *
 * @author maya
 */
public class GananciasPorUsuarioTO {

    private Double montoGanado;
    private String nombreEvento;
    private Date fechaEvento;

    public GananciasPorUsuarioTO() {
    }

    public GananciasPorUsuarioTO(Double montoGanado, String nombreEvento, Date fechaEvento) {
        this.montoGanado = montoGanado;
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Double getMontoGanado() {
        return montoGanado;
    }

    public void setMontoGanado(Double montoGanado) {
        this.montoGanado = montoGanado;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

}
