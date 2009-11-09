/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dominio;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author maya
 */
@Entity
@Table(name = "users")
@NamedQueries({@NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"), @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"), @NamedQuery(name = "Users.findByNombre", query = "SELECT u FROM Users u WHERE u.nombre = :nombre"), @NamedQuery(name = "Users.findByApellido", query = "SELECT u FROM Users u WHERE u.apellido = :apellido"), @NamedQuery(name = "Users.findByFechaNacimiento", query = "SELECT u FROM Users u WHERE u.fechaNacimiento = :fechaNacimiento"), @NamedQuery(name = "Users.findBySexo", query = "SELECT u FROM Users u WHERE u.sexo = :sexo"), @NamedQuery(name = "Users.findByCorreo", query = "SELECT u FROM Users u WHERE u.correo = :correo"), @NamedQuery(name = "Users.findByTelefono", query = "SELECT u FROM Users u WHERE u.telefono = :telefono"), @NamedQuery(name = "Users.findByPais", query = "SELECT u FROM Users u WHERE u.pais = :pais"), @NamedQuery(name = "Users.findByCiudad", query = "SELECT u FROM Users u WHERE u.ciudad = :ciudad"), @NamedQuery(name = "Users.findByCodigoPostal", query = "SELECT u FROM Users u WHERE u.codigoPostal = :codigoPostal"), @NamedQuery(name = "Users.findByEstado", query = "SELECT u FROM Users u WHERE u.estado = :estado"), @NamedQuery(name = "Users.findByCalle", query = "SELECT u FROM Users u WHERE u.calle = :calle"), @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"), @NamedQuery(name = "Users.findByEnabled", query = "SELECT u FROM Users u WHERE u.enabled = :enabled"), @NamedQuery(name = "Users.findByConfirmado", query = "SELECT u FROM Users u WHERE u.confirmado = :confirmado"), @NamedQuery(name = "Users.findByAvatar", query = "SELECT u FROM Users u WHERE u.avatar = :avatar")})
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Basic(optional = false)
    @Column(name = "sexo")
    private String sexo;
    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "pais")
    private String pais;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "codigoPostal")
    private Integer codigoPostal;
    @Column(name = "estado")
    private String estado;
    @Column(name = "calle")
    private String calle;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "enabled")
    private boolean enabled;
    @Basic(optional = false)
    @Column(name = "confirmado")
    private boolean confirmado;
    @Column(name = "avatar")
    private String avatar;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<UsuarioMedioPago> usuarioMedioPagoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Apuesta> apuestaCollection;

    public Users() {
    }

    public Users(String username) {
        this.username = username;
    }

    public Users(String username, String nombre, String apellido, String sexo, String correo, String password, boolean enabled, boolean confirmado) {
        this.username = username;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.correo = correo;
        this.password = password;
        this.enabled = enabled;
        this.confirmado = confirmado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dominio.Users[username=" + username + "]";
    }

}
