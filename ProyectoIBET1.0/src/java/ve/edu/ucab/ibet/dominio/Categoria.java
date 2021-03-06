package ve.edu.ucab.ibet.dominio;

import java.io.Serializable;
import java.util.Collection;
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

/**
 * Clase para almacenar informacion de los objetos de dominio categoria
 * @author Gerardo Barcia
 * @version 1.0
 */
@Entity
@Table(name = "CATEGORIA")
@NamedQueries({@NamedQuery(name = "Categoria.findAll", query = "SELECT c FROM Categoria c")})
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "empate")
    private boolean empate;
    @Basic(optional = false)
    @Column(name = "logicaAutomatica")
    private boolean logicaAutomatica;
    @Basic(optional = false)
    @Column(name = "habilitada")
    private boolean habilitada;
    @Column(name = "participantesComun")
    private boolean participantesComun;
    @Column(name = "nombreLogica")
    private String nombreLogica;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCategoria")
    private Collection<Evento> eventoCollection;
    @OneToMany(mappedBy = "idCategoria")
    private Collection<Categoria> categoriaCollection;
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    @ManyToOne
    private Categoria idCategoria;

    public Categoria() {
    }

    public Categoria(Integer id) {
        this.id = id;
    }

    public Categoria(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Categoria(Integer id, String nombre, Categoria idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.idCategoria = idCategoria;
    }
    
    public Categoria(Integer id, String nombre, boolean empate, boolean logicaAutomatica) {
        this.id = id;
        this.nombre = nombre;
        this.empate = empate;
        this.logicaAutomatica = logicaAutomatica;
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

    public boolean getEmpate() {
        return empate;
    }

    public void setEmpate(boolean empate) {
        this.empate = empate;
    }

    public boolean getLogicaAutomatica() {
        return logicaAutomatica;
    }

    public void setLogicaAutomatica(boolean logicaAutomatica) {
        this.logicaAutomatica = logicaAutomatica;
    }

    public String getNombreLogica() {
        return nombreLogica;
    }

    public void setNombreLogica(String nombreLogica) {
        this.nombreLogica = nombreLogica;
    }

    public Collection<Evento> getEventoCollection() {
        return eventoCollection;
    }

    public void setEventoCollection(Collection<Evento> eventoCollection) {
        this.eventoCollection = eventoCollection;
    }

    public Collection<Categoria> getCategoriaCollection() {
        return categoriaCollection;
    }

    public void setCategoriaCollection(Collection<Categoria> categoriaCollection) {
        this.categoriaCollection = categoriaCollection;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public boolean isHabilitada() {
        return habilitada;
    }

    public void setHabilitada(boolean habilitada) {
        this.habilitada = habilitada;
    }

    public boolean isParticipantesComun() {
        return participantesComun;
    }

    public void setParticipantesComun(boolean participantesComun) {
        this.participantesComun = participantesComun;
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
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ve.edu.ucab.ibet.dominio.Categoria[id=" + id + "]";
    }

}
