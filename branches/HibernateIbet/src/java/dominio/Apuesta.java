/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name = "APUESTA", catalog = "iBet", schema = "")
@NamedQueries({@NamedQuery(name = "Apuesta.findAll", query = "SELECT a FROM Apuesta a"), @NamedQuery(name = "Apuesta.findByUsername", query = "SELECT a FROM Apuesta a WHERE a.apuestaPK.username = :username"), @NamedQuery(name = "Apuesta.findByIdMedioPago", query = "SELECT a FROM Apuesta a WHERE a.apuestaPK.idMedioPago = :idMedioPago"), @NamedQuery(name = "Apuesta.findByFecha", query = "SELECT a FROM Apuesta a WHERE a.fecha = :fecha"), @NamedQuery(name = "Apuesta.findByMonto", query = "SELECT a FROM Apuesta a WHERE a.monto = :monto"), @NamedQuery(name = "Apuesta.findByGano", query = "SELECT a FROM Apuesta a WHERE a.gano = :gano"), @NamedQuery(name = "Apuesta.findByEmpato", query = "SELECT a FROM Apuesta a WHERE a.empato = :empato")})
public class Apuesta implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ApuestaPK apuestaPK;
    @Basic(optional = false)
    @Column(name = "fecha", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(name = "monto", nullable = false)
    private double monto;
    @Column(name = "ganador")
    private boolean ganador;
    @Column(name = "gano")
    private Boolean gano;
    @Column(name = "empato")
    private Boolean empato;
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "idMedioPago", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private MedioPago medioPago;
    @JoinColumns({@JoinColumn(name = "idEvento", referencedColumnName = "idEvento", nullable = false), @JoinColumn(name = "idParticipante", referencedColumnName = "idParticipante", nullable = false)})
    @ManyToOne(optional = false)
    private TableroGanancia tableroGanancia;

    public Apuesta() {
    }

    public Apuesta(ApuestaPK apuestaPK) {
        this.apuestaPK = apuestaPK;
    }

    public Apuesta(ApuestaPK apuestaPK, Date fecha, double monto) {
        this.apuestaPK = apuestaPK;
        this.fecha = fecha;
        this.monto = monto;
    }

    public Apuesta(String username, int idMedioPago) {
        this.apuestaPK = new ApuestaPK(username, idMedioPago);
    }

    public ApuestaPK getApuestaPK() {
        return apuestaPK;
    }

    public void setApuestaPK(ApuestaPK apuestaPK) {
        this.apuestaPK = apuestaPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isGanador() {
        return ganador;
    }

    public void setGanador(boolean ganador) {
        this.ganador = ganador;
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

    public TableroGanancia getTableroGanancia() {
        return tableroGanancia;
    }

    public void setTableroGanancia(TableroGanancia tableroGanancia) {
        this.tableroGanancia = tableroGanancia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (apuestaPK != null ? apuestaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Apuesta)) {
            return false;
        }
        Apuesta other = (Apuesta) object;
        if ((this.apuestaPK == null && other.apuestaPK != null) || (this.apuestaPK != null && !this.apuestaPK.equals(other.apuestaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Apuesta[apuestaPK=" + apuestaPK + "]";
    }

}
