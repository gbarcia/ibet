package ve.edu.ucab.ibet.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clase para representar la clave primaria compuesta de TableroGanancia
 * @author Gerardo Barcia
 * @version 1.0
 */
@Embeddable
public class TableroGananciaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idEvento")
    private int idEvento;
    @Basic(optional = false)
    @Column(name = "idParticipante")
    private int idParticipante;

    public TableroGananciaPK() {
    }

    public TableroGananciaPK(int idEvento, int idParticipante) {
        this.idEvento = idEvento;
        this.idParticipante = idParticipante;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public int getIdParticipante() {
        return idParticipante;
    }

    public void setIdParticipante(int idParticipante) {
        this.idParticipante = idParticipante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEvento;
        hash += (int) idParticipante;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TableroGananciaPK)) {
            return false;
        }
        TableroGananciaPK other = (TableroGananciaPK) object;
        if (this.idEvento != other.idEvento) {
            return false;
        }
        if (this.idParticipante != other.idParticipante) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.edu.ucab.ibet.dominio.TableroGananciaPK[idEvento=" + idEvento + ", idParticipante=" + idParticipante + "]";
    }

}
