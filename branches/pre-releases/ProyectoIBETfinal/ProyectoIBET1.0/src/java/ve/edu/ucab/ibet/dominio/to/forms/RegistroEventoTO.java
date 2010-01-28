package ve.edu.ucab.ibet.dominio.to.forms;

import java.io.Serializable;
import java.util.Date;
import org.springframework.web.multipart.MultipartFile;
import ve.edu.ucab.ibet.dominio.Categoria;
import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.Politica;
import ve.edu.ucab.ibet.dominio.TableroGanancia;

/**
 * Clase para almacenar la informacion del registro y manipulacion de la
 * informacion para los eventos
 * @author Gerardo Barcia
 * @version 1.0
 */
public class RegistroEventoTO implements Serializable {

    private Categoria categoria;
    private Politica politica;
    private Participante participanteUno;
    private Participante participanteDos;
    private TableroGanancia tableroGananciaUno;
    private TableroGanancia tableroGananciaDos;
    private String nombreEvento;
    private String horaEvento;
    private Date fechaEvento;
    private String horaMax;
    private Date fechaMax;
    private MultipartFile imagenEvento;

    public RegistroEventoTO() {
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Date getFechaMax() {
        return fechaMax;
    }

    public void setFechaMax(Date fechaMax) {
        this.fechaMax = fechaMax;
    }

    public String getHoraEvento() {
        return horaEvento;
    }

    public void setHoraEvento(String horaEvento) {
        this.horaEvento = horaEvento;
    }

    public String getHoraMax() {
        return horaMax;
    }

    public void setHoraMax(String horaMax) {
        this.horaMax = horaMax;
    }

    public MultipartFile getImagenEvento() {
        return imagenEvento;
    }

    public void setImagenEvento(MultipartFile imagenEvento) {
        this.imagenEvento = imagenEvento;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Participante getParticipanteDos() {
        return participanteDos;
    }

    public void setParticipanteDos(Participante participanteDos) {
        this.participanteDos = participanteDos;
    }

    public Participante getParticipanteUno() {
        return participanteUno;
    }

    public void setParticipanteUno(Participante participanteUno) {
        this.participanteUno = participanteUno;
    }

    public Politica getPolitica() {
        return politica;
    }

    public void setPolitica(Politica politica) {
        this.politica = politica;
    }

    public TableroGanancia getTableroGananciaDos() {
        return tableroGananciaDos;
    }

    public void setTableroGananciaDos(TableroGanancia tableroGananciaDos) {
        this.tableroGananciaDos = tableroGananciaDos;
    }

    public TableroGanancia getTableroGananciaUno() {
        return tableroGananciaUno;
    }

    public void setTableroGananciaUno(TableroGanancia tableroGananciaUno) {
        this.tableroGananciaUno = tableroGananciaUno;
    }
}
