package ve.edu.ucab.ibet.dominio.to.forms;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase TO para el formulario de registro usuarios
 * @author Gerardo Barcia
 * @version 1.0
 */
public class RegistroUsuarioTO implements Serializable {

    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private String sexo;
    private String correo;
    private String repCorreo;
    private String telefono;
    private String pais;
    private String ciudad;
    private String codigoPostal;
    private String estado;
    private String calle;
    private String clave;
    private String repClave;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRepClave() {
        return repClave;
    }

    public void setRepClave(String repClave) {
        this.repClave = repClave;
    }

    public String getRepCorreo() {
        return repCorreo;
    }

    public void setRepCorreo(String repCorreo) {
        this.repCorreo = repCorreo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
