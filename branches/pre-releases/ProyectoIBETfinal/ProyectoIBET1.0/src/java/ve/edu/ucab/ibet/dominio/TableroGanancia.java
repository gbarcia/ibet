package ve.edu.ucab.ibet.dominio;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase para almacenar informacion de los objetos de dominio Tablero de Ganancia
 * en este objeto de dominio se expresaran los paneles de proporcion de ganar
 * y empatar de los eventos para los distintos participantes
 * @author Gerardo Barcia
 * @version 1.0
 */
@Entity
@Table(name = "TABLERO_GANANCIA")
@NamedQueries({@NamedQuery(name = "TableroGanancia.findAll", query = "SELECT t FROM TableroGanancia t")})
public class TableroGanancia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TableroGananciaPK tableroGananciaPK;
    @Column(name = "gano")
    private Boolean gano;
    @Column(name = "empato")
    private Boolean empato;
    @Basic(optional = false)
    @Column(name = "propocionGano")
    private Double proporcionGano;
    @Column(name = "proporcionEmpate")
    private Double proporcionEmpate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tableroGanancia")
    private Collection<Apuesta> apuestaCollection;
    @JoinColumn(name = "idEvento", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Evento evento;
    @JoinColumn(name = "idParticipante", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Participante participante;

    public TableroGanancia() {
    }

    public TableroGanancia(TableroGananciaPK tableroGananciaPK) {
        this.tableroGananciaPK = tableroGananciaPK;
    }

    public TableroGanancia(TableroGananciaPK tableroGananciaPK, Double proporcionGano) {
        this.tableroGananciaPK = tableroGananciaPK;
        this.proporcionGano = proporcionGano;
    }

    public TableroGanancia(int idEvento, int idParticipante) {
        this.tableroGananciaPK = new TableroGananciaPK(idEvento, idParticipante);
    }

    public TableroGananciaPK getTableroGananciaPK() {
        return tableroGananciaPK;
    }

    public void setTableroGananciaPK(TableroGananciaPK tableroGananciaPK) {
        this.tableroGananciaPK = tableroGananciaPK;
    }

    public Boolean getGano() {
        return gano;
    }

    public void setGano(Boolean gano) {
        this.gano = gano;
    }

    public Boolean getEmpato() {
        return empato;
    }

    public void setEmpato(Boolean empato) {
        this.empato = empato;
    }

    public Double getPropocionGano() {
        return proporcionGano;
    }

    public void setPropocionGano(Double proporcionGano) {
        this.proporcionGano = proporcionGano;
    }

    public Double getProporcionEmpate() {
        return proporcionEmpate;
    }

    public void setProporcionEmpate(Double proporcionEmpate) {
        this.proporcionEmpate = proporcionEmpate;
    }

    public Collection<Apuesta> getApuestaCollection() {
        return apuestaCollection;
    }

    public void setApuestaCollection(Collection<Apuesta> apuestaCollection) {
        this.apuestaCollection = apuestaCollection;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tableroGananciaPK != null ? tableroGananciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TableroGanancia)) {
            return false;
        }
        TableroGanancia other = (TableroGanancia) object;
        if ((this.tableroGananciaPK == null && other.tableroGananciaPK != null) || (this.tableroGananciaPK != null && !this.tableroGananciaPK.equals(other.tableroGananciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.edu.ucab.ibet.dominio.TableroGanancia[tableroGananciaPK=" + tableroGananciaPK + "]";
    }

}
