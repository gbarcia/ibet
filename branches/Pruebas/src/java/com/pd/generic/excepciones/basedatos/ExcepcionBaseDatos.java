/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pd.generic.excepciones.basedatos;

import com.pd.generic.excepciones.GeneralException;

/**
 *
 * @author nath
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
