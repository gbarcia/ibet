package ve.edu.ucab.ibet.dominio.to.forms;

import java.io.Serializable;

/**
 * Clase TO para el formulario de solicitud de recuperacion de clave
 * @author Gerardo Barcia
 * @version 1.0
 */
public class RecuperarClaveTO implements Serializable {

    private String username;

    private String correo;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
