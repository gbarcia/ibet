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
 * @author maya
 */
@Embeddable
public class ApuestaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "idMedioPago")
    private int idMedioPago;

    public ApuestaPK() {
    }

    public ApuestaPK(int id, String username, int idMedioPago) {
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
        if (!(object instanceof ApuestaPK)) {
            return false;
        }
        ApuestaPK other = (ApuestaPK) object;
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
        return "dominio.ApuestaPK[id=" + id + ", username=" + username + ", idMedioPago=" + idMedioPago + "]";
    }

}
