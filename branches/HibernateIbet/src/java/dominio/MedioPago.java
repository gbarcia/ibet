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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "MEDIO_PAGO", uniqueConstraints = {@UniqueConstraint(columnNames = {"nombre"})})
@NamedQueries({@NamedQuery(name = "MedioPago.findAll", query = "SELECT m FROM MedioPago m")})
public class MedioPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    @Basic(optional = false)
    @Column(name = "activo", nullable = false)
    private boolean activo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medioPago")
    private Collection<UsuarioMedioPago> usuarioMedioPagoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medioPago")
    private Collection<Apuesta> apuestaCollection;

    public MedioPago() {
    }

    public MedioPago(Integer id) {
        this.id = id;
    }

    public MedioPago(Integer id, String nombre, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.activo = activo;
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

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Collection<UsuarioMedioPago> getUsuarioMedioPagoCollection() {
        return usuarioMedioPagoCollection;
    }

    public void setUsuarioMedioPagoCollection(Collection<UsuarioMedioPago> usuarioMedioPagoCollection) {
        this.usuarioMedioPagoCollection = usuarioMedioPagoCollection;
    }

    public Collection<Apuesta> getApuestaCollection() {
        return apuestaCollection;
    }

    public void setApuestaCollection(Collection<Apuesta> apuestaCollection) {
        this.apuestaCollection = apuestaCollection;
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
        if (!(object instanceof MedioPago)) {
            return false;
        }
        MedioPago other = (MedioPago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.MedioPago[id=" + id + "]";
    }

}
