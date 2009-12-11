/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author nath
 */
@Embeddable
public class UsuarioMedioPagoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private int id;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 250)
    private String username;
    @Basic(optional = false)
    @Column(name = "idMedioPago", nullable = false)
    private int idMedioPago;

    public UsuarioMedioPagoPK() {
    }

    public UsuarioMedioPagoPK(int id, String username, int idMedioPago) {
        this.id = id;
        this.username = username;
        this.idMedioPago = idMedioPago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        hash += (int) id;
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
        if (this.id != other.id) {
            return false;
        }
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
        return "dominio.UsuarioMedioPagoPK[id=" + id + ", username=" + username + ", idMedioPago=" + idMedioPago + "]";
    }

}
