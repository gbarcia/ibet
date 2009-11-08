package ve.edu.ucab.ibet.generic.excepciones;

import org.apache.log4j.spi.ErrorCode;

/**
 * Excepcion general de la aplicacion
 * @author Gerardo Barcia
 * @version 2.0
 */
public abstract class GeneralException extends RuntimeException implements ErrorCode {

    private String keyError;
    private Exception innerException;
    private String descripcion;
    private boolean sourceException;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Exception getInnerException() {
        return innerException;
    }

    public void setInnerException(Exception innerException) {
        this.innerException = innerException;
    }

    public String getKeyError() {
        return keyError;
    }

    public void setKeyError(String keyError) {
        this.keyError = keyError;
    }

    public boolean isSourceException() {
        return sourceException;
    }

    public void setSourceException(boolean sourceException) {
        this.sourceException = sourceException;
    }
}
