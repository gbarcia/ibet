/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.pd.servicios.interfaces;

import com.pd.dominio.Cliente;
import com.pd.generic.excepciones.GeneralException;
import java.util.List;

/**
 *
 * @author nath
 */
public interface IServicioCliente {
    public void crearCliente (Cliente cliente) throws GeneralException;
    public void actualizarCliente (Cliente cliente) throws GeneralException;
    public List<Cliente> listarClientes () throws GeneralException;
}
