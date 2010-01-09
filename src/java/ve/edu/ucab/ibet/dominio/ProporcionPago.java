package ve.edu.ucab.ibet.dominio;

import java.io.Serializable;

/**
 * Clase de dominio para albergar las proporciones de pago de las apuestas
 * segun las estrategias a seguir para su fijacion
 * NOTA: Esta clase de dominio trasciende a el almacen en la base de datos
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ProporcionPago implements Serializable {

    private Double pagoPorGanar;
    private Double pagoPorEmpate;
    private Double pagoPorPerder;

    public ProporcionPago() {
    }

    public Double getPagoPorEmpate() {
        return pagoPorEmpate;
    }

    public void setPagoPorEmpate(Double pagoPorEmpate) {
        this.pagoPorEmpate = pagoPorEmpate;
    }

    public Double getPagoPorGanar() {
        return pagoPorGanar;
    }

    public void setPagoPorGanar(Double pagoPorGanar) {
        this.pagoPorGanar = pagoPorGanar;
    }

    public Double getPagoPorPerder() {
        return pagoPorPerder;
    }

    public void setPagoPorPerder(Double pagoPorPerder) {
        this.pagoPorPerder = pagoPorPerder;
    }
}
