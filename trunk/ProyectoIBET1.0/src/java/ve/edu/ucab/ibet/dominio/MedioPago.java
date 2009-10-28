package ve.edu.ucab.ibet.dominio;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase para representar los objetos de dominio de medios de pago
 * @author Gerardo Barcia
 * @version 1.0
 */
@Entity
@Table(name = "MEDIO_PAGO")
public class MedioPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "activo")
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
        return "ve.edu.ucab.ibet.dominio.MedioPago[id=" + id + "]";
    }

}
