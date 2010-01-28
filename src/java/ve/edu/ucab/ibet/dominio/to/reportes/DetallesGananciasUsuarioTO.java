package ve.edu.ucab.ibet.dominio.to.reportes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maya
 */
public class DetallesGananciasUsuarioTO {

    private List<GananciasPorUsuarioTO> gananciasPorUsuario = new ArrayList<GananciasPorUsuarioTO>();
    private Double montoTotal = 0.0;

    public DetallesGananciasUsuarioTO() {
    }

    public List<GananciasPorUsuarioTO> getGananciasPorUsuario() {
        return gananciasPorUsuario;
    }

    public void setGananciasPorUsuario(List<GananciasPorUsuarioTO> gananciasPorUsuario) {
        this.gananciasPorUsuario = gananciasPorUsuario;
    }

    public Double getMontoTotal() {
        if (!gananciasPorUsuario.isEmpty()) {
            for (GananciasPorUsuarioTO gananciasPorUsuarioTO : gananciasPorUsuario) {
                montoTotal += gananciasPorUsuarioTO.getMontoGanado();
            }
        }
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }
}
