package ve.edu.ucab.ibet.controllers.forms;

import java.io.Serializable;

/**
 * Clase de tipo objeto de transferencia para almacenar los datos del formulario
 * de categoria
 * @author Gerardo Barcia
 * @version 1.0
 */
public class RegistroCategoriaTO implements Serializable {

    private String nombreCategoria;
    private String jerarquia;
    private String empate;
    private String logicaAutomatica;
    private String nombreLogica;

    public RegistroCategoriaTO() {
    }

    public String getEmpate() {
        return empate;
    }

    public void setEmpate(String empate) {
        this.empate = empate;
    }

    public String getJerarquia() {
        return jerarquia;
    }

    public void setJerarquia(String jerarquia) {
        this.jerarquia = jerarquia;
    }

    public String getLogicaAutomatica() {
        return logicaAutomatica;
    }

    public void setLogicaAutomatica(String logicaAutomatica) {
        this.logicaAutomatica = logicaAutomatica;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreLogica() {
        return nombreLogica;
    }

    public void setNombreLogica(String nombreLogica) {
        this.nombreLogica = nombreLogica;
    }
}
