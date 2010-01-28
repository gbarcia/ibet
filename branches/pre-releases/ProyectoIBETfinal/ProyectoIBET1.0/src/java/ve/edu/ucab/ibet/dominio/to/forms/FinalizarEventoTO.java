package ve.edu.ucab.ibet.dominio.to.forms;

import java.io.Serializable;

/**
 * Clase de objeto de transferencia para el paso de informacion para finalizar
 * un evento
 * @author Gerardo Barcia
 * @version 1.0
 */
public class FinalizarEventoTO implements Serializable {

    private String resultado;

    private Integer idParticipanteGanador;

    private Boolean partidaEmpatada;

    public FinalizarEventoTO() {
    }

    public Integer getIdParticipanteGanador() {
        return idParticipanteGanador;
    }

    public void setIdParticipanteGanador(Integer idParticipanteGanador) {
        this.idParticipanteGanador = idParticipanteGanador;
    }

    public Boolean getPartidaEmpatada() {
        return partidaEmpatada;
    }

    public void setPartidaEmpatada(Boolean partidaEmpatada) {
        this.partidaEmpatada = partidaEmpatada;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

}
