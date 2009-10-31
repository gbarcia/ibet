/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.dominio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nath
 */
@Entity
@Table(name = "USUARIO_MEDIO_PAGO")
@NamedQueries({@NamedQuery(name = "UsuarioMedioPago.findAll", query = "SELECT u FROM UsuarioMedioPago u")})
public class UsuarioMedioPago implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UsuarioMedioPagoPK usuarioMedioPagoPK;
    @Basic(optional = false)
    @Column(name = "activo")
    private boolean activo;
    @Basic(optional = false)
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Basic(optional = false)
    @Column(name = "montoMaximo")
    private double montoMaximo;
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "idMedioPago", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MedioPago medioPago;

    public UsuarioMedioPago() {
    }

    public UsuarioMedioPago(UsuarioMedioPagoPK usuarioMedioPagoPK) {
        this.usuarioMedioPagoPK = usuarioMedioPagoPK;
    }

    public UsuarioMedioPago(UsuarioMedioPagoPK usuarioMedioPagoPK, boolean activo, Date fechaInicio, double montoMaximo) {
        this.usuarioMedioPagoPK = usuarioMedioPagoPK;
        this.activo = activo;
        this.fechaInicio = fechaInicio;
        this.montoMaximo = montoMaximo;
    }

    public UsuarioMedioPago(String username, int idMedioPago) {
        this.usuarioMedioPagoPK = new UsuarioMedioPagoPK(username, idMedioPago);
    }

    public UsuarioMedioPagoPK getUsuarioMedioPagoPK() {
        return usuarioMedioPagoPK;
    }

    public void setUsuarioMedioPagoPK(UsuarioMedioPagoPK usuarioMedioPagoPK) {
        this.usuarioMedioPagoPK = usuarioMedioPagoPK;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public double getMontoMaximo() {
        return montoMaximo;
    }

    public void setMontoMaximo(double montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public MedioPago getMedioPago() {
        return medioPago;
    }

    public void setMedioPago(MedioPago medioPago) {
        this.medioPago = medioPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioMedioPagoPK != null ? usuarioMedioPagoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioMedioPago)) {
            return false;
        }
        UsuarioMedioPago other = (UsuarioMedioPago) object;
        if ((this.usuarioMedioPagoPK == null && other.usuarioMedioPagoPK != null) || (this.usuarioMedioPagoPK != null && !this.usuarioMedioPagoPK.equals(other.usuarioMedioPagoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.edu.ucab.ibet.dominio.UsuarioMedioPago[usuarioMedioPagoPK=" + usuarioMedioPagoPK + "]";
    }

}
