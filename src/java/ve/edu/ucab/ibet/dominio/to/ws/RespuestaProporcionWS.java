package ve.edu.ucab.ibet.dominio.to.ws;

import java.io.Serializable;

/**
 * Clase para la estructura de respuesta del servicio web para la consulta
 * de proporciones pagadas por los equipos en un evento determinado
 * @author Gerardo Barcia
 * @version 1.0
 */
public class RespuestaProporcionWS implements Serializable {

    private String equipoUno;
    private String equipoDos;
    private Double proporcionEquipoUno;
    private Double proporcionEquipoDos;

    public RespuestaProporcionWS() {
    }

    public String getEquipoDos() {
        return equipoDos;
    }

    public void setEquipoDos(String equipoDos) {
        this.equipoDos = equipoDos;
    }

    public String getEquipoUno() {
        return equipoUno;
    }

    public void setEquipoUno(String equipoUno) {
        this.equipoUno = equipoUno;
    }

    public Double getProporcionEquipoDos() {
        return proporcionEquipoDos;
    }

    public void setProporcionEquipoDos(Double proporcionEquipoDos) {
        this.proporcionEquipoDos = proporcionEquipoDos;
    }

    public Double getProporcionEquipoUno() {
        return proporcionEquipoUno;
    }

    public void setProporcionEquipoUno(Double proporcionEquipoUno) {
        this.proporcionEquipoUno = proporcionEquipoUno;
    }
}
