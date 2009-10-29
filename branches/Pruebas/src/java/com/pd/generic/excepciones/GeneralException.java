/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pd.generic.excepciones;

/**
 *
 * @author nath
 */
public abstract class GeneralException extends Exception {
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
