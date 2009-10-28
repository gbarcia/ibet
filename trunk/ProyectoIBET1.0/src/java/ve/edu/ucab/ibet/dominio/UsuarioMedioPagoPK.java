package ve.edu.ucab.ibet.dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Clase para representar la clave primaria compuesta de UsuarioMediosPago
 * @author GerardoBarcia
 * @version 1.0
 */
@Embeddable
public class UsuarioMedioPagoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "idMedioPago")
    private int idMedioPago;

    public UsuarioMedioPagoPK() {
    }

    public UsuarioMedioPagoPK(String username, int idMedioPago) {
        this.username = username;
        this.idMedioPago = idMedioPago;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdMedioPago() {
        return idMedioPago;
    }

    public void setIdMedioPago(int idMedioPago) {
        this.idMedioPago = idMedioPago;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        hash += (int) idMedioPago;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioMedioPagoPK)) {
            return false;
        }
        UsuarioMedioPagoPK other = (UsuarioMedioPagoPK) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        if (this.idMedioPago != other.idMedioPago) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.edu.ucab.ibet.dominio.UsuarioMedioPagoPK[username=" + username + ", idMedioPago=" + idMedioPago + "]";
    }

}
