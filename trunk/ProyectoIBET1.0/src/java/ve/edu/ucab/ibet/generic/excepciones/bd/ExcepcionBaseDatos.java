package ve.edu.ucab.ibet.generic.excepciones.bd;

import ve.edu.ucab.ibet.generic.excepciones.GeneralException;

/**
 * Clase para el manejo de excepciones de base de datos
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ExcepcionBaseDatos extends GeneralException {

    private static final long serialVersionUID = 1L;

    public ExcepcionBaseDatos() {
    }

    public ExcepcionBaseDatos(String mensaje) {
        this.setKeyError(mensaje);
    }

    public ExcepcionBaseDatos(Exception e) {
        this.setInnerException(e);
    }

    public ExcepcionBaseDatos(Exception e, String mensaje) {
        this.setInnerException(e);
        this.setKeyError(mensaje);
    }
}
