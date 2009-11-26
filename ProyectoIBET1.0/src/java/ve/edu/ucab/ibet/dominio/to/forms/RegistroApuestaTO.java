package ve.edu.ucab.ibet.dominio.to.forms;

import java.io.Serializable;
import java.util.Date;

/**
 * Objeto de transferencia para el registro de una apuesta
 * @author Gerardo Barcia
 * @version 1.0
 */
public class RegistroApuestaTO implements Serializable {

    private Double montoApuesta;

    private String nombreEvento;

    private Date fechaEvento;

    private String nombreMetodoPago;

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Double getMontoApuesta() {
        return montoApuesta;
    }

    public void setMontoApuesta(Double montoApuesta) {
        this.montoApuesta = montoApuesta;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getNombreMetodoPago() {
        return nombreMetodoPago;
    }

    public void setNombreMetodoPago(String nombreMetodoPago) {
        this.nombreMetodoPago = nombreMetodoPago;
    }
}
