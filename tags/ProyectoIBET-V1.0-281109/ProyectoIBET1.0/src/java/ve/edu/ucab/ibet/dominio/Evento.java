/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author nath
 */
@Entity
@Table(name = "EVENTO")
@NamedQueries({@NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")})
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fechaEvento;
    @Basic(optional = false)
    @Column(name = "hora")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @Column(name = "fechaMaxima")
    @Temporal(TemporalType.DATE)
    private Date fechaMaxima;
    @Basic(optional = false)
    @Column(name = "horaMaxima")
    @Temporal(TemporalType.TIME)
    private Date horaMaxima;
    @Basic(optional = false)
    @Column(name = "resultado")
    private String resultado;
    @Basic(optional = false)
    @Column(name = "estatus")
    private boolean estatus;
    @Column(name = "finalizado")
    private boolean finalizado;
    @Column(name = "imagenEvento")
    private String imagenEvento;
    @JoinColumn(name = "idPolitica", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Politica idPolitica;
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categoria idCategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
    private Collection<TableroGanancia> tableroGananciaCollection;

    public Evento() {
    }

    public Evento(Integer id) {
        this.id = id;
    }

    public Evento(Integer id, String nombre, Date fechaEvento, Date hora, Date fechaMaxima, Date horaMaxima, String resultado, boolean estatus) {
        this.id = id;
        this.nombre = nombre;
        this.fechaEvento = fechaEvento;
        this.hora = hora;
        this.fechaMaxima = fechaMaxima;
        this.horaMaxima = horaMaxima;
        this.resultado = resultado;
        this.estatus = estatus;
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

    public Date getFecha() {
        return fechaEvento;
    }

    public void setFecha(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Date getFechaMaxima() {
        return fechaMaxima;
    }

    public void setFechaMaxima(Date fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public Date getHoraMaxima() {
        return horaMaxima;
    }

    public void setHoraMaxima(Date horaMaxima) {
        this.horaMaxima = horaMaxima;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
    
    public String getImagenEvento() {
        return imagenEvento;
    }

    public void setImagenEvento(String imagenEvento) {
        this.imagenEvento = imagenEvento;
    }

    public Politica getIdPolitica() {
        return idPolitica;
    }

    public void setIdPolitica(Politica idPolitica) {
        this.idPolitica = idPolitica;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
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
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.edu.ucab.ibet.dominio.Evento[id=" + id + "]";
    }

}
