package ve.edu.ucab.ibet.generic.excepciones.negocio;

import org.springframework.core.ErrorCoded;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;

/**
 * Clase para el manejo de excepciones de negocio
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ExcepcionNegocio extends GeneralException implements ErrorCoded {

    private static final long serialVersionUID = 1L;

    public ExcepcionNegocio() {
    }

    public ExcepcionNegocio(String mensaje) {
        this.setKeyError(mensaje);
    }

    public ExcepcionNegocio(Exception e) {
        this.setInnerException(e);
    }

    public ExcepcionNegocio(Exception e, String mensaje) {
        this.setInnerException(e);
        this.setKeyError(mensaje);
    }

    public String getErrorCode() {
        return this.getClass().getSimpleName();
    }
}
