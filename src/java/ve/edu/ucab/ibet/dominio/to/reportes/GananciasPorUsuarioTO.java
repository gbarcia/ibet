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
    private Boolean gano;
    private Double proporcionGano;
    private Double proporcionEmpate;

    public GananciasPorUsuarioTO() {
    }

    public GananciasPorUsuarioTO(Double montoGanado, String nombreEvento, Date fechaEvento, Boolean gano, Double proporcionGano, Double proporcionEmpate) {
        this.montoGanado = montoGanado;
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
        this.gano = gano;
        this.proporcionGano = proporcionGano;
        this.proporcionEmpate = proporcionEmpate;
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

    public Boolean getGano() {
        return gano;
    }

    public void setGano(Boolean gano) {
        this.gano = gano;
    }

    public Double getProporcionEmpate() {
        return proporcionEmpate;
    }

    public void setProporcionEmpate(Double proporcionEmpate) {
        this.proporcionEmpate = proporcionEmpate;
    }

    public Double getProporcionGano() {
        return proporcionGano;
    }

    public void setProporcionGano(Double proporcionGano) {
        this.proporcionGano = proporcionGano;
    }

}
