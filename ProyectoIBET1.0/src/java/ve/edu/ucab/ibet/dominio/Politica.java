/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.dominio;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author nath
 */
@Entity
@Table(name = "POLITICA")
public class Politica implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "montoMaximo")
    private double montoMaximo;
    @Basic(optional = false)
    @Column(name = "finalizarAntes")
    private boolean finalizarAntes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPolitica")
    private Collection<Evento> eventoCollection;

    public Politica() {
    }

    public Politica(Integer id) {
        this.id = id;
    }

    public Politica(Integer id, double montoMaximo, boolean finalizarAntes) {
        this.id = id;
        this.montoMaximo = montoMaximo;
        this.finalizarAntes = finalizarAntes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(double montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public boolean getFinalizarAntes() {
        return finalizarAntes;
    }

    public void setFinalizarAntes(boolean finalizarAntes) {
        this.finalizarAntes = finalizarAntes;
    }

    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Politica)) {
            return false;
        }
        Politica other = (Politica) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.edu.ucab.ibet.dominio.Politica[id=" + id + "]";
    }

}
