/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;

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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author nath
 */
@Entity
@Table(name = "PARTICIPANTE", catalog = "iBet", schema = "", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
@NamedQueries({@NamedQuery(name = "Participante.findAll", query = "SELECT p FROM Participante p"), @NamedQuery(name = "Participante.findById", query = "SELECT p FROM Participante p WHERE p.id = :id"), @NamedQuery(name = "Participante.findByNombre", query = "SELECT p FROM Participante p WHERE p.nombre = :nombre"), @NamedQuery(name = "Participante.findByDescripcion", query = "SELECT p FROM Participante p WHERE p.descripcion = :descripcion")})
public class Participante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @Column(name = "descripcion", length = 45)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "participante")
    private Collection<TableroGanancia> tableroGananciaCollection;

    public Participante() {
    }

    public Participante(Integer id) {
        this.id = id;
    }

    public Participante(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<TableroGanancia> getTableroGananciaCollection() {
        return tableroGananciaCollection;
    }

    public void setTableroGananciaCollection(Collection<TableroGanancia> tableroGananciaCollection) {
        this.tableroGananciaCollection = tableroGananciaCollection;
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
        if (!(object instanceof Participante)) {
            return false;
        }
        Participante other = (Participante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Participante[id=" + id + "]";
    }

}
