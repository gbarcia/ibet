/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pd.generic.excepciones.negocio;

import com.pd.generic.excepciones.GeneralException;

/**
 *
 * @author nath
 */
public class ExcepcionNegocio extends GeneralException{
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
}
