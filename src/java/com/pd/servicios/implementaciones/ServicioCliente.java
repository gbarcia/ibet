/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pd.servicios.implementaciones;

import com.pd.dominio.Cliente;
import com.pd.generic.dao.interfaces.IGenericDao;
import com.pd.generic.excepciones.GeneralException;
import com.pd.generic.excepciones.basedatos.ExcepcionBaseDatos;
import com.pd.servicios.interfaces.IServicioCliente;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author nath
 */
public class ServicioCliente implements IServicioCliente {

    private IGenericDao genericDao;

    public IGenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(IGenericDao genericDao) {
        this.genericDao = genericDao;
    }
    @Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
    public void crearCliente(Cliente cliente) throws GeneralException {
        try {
            Integer idCliente = genericDao.getNextId(cliente);
            cliente.setId(idCliente);
            genericDao.insertar(cliente);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcepcionBaseDatos(e, "Error al registrar el cliente");
        }
    }

    public void actualizarCliente(Cliente cliente) throws GeneralException {
        try {
            Cliente clienteNombre = (Cliente) genericDao.findByPropertyUnique(Cliente.class, "nombre", cliente.getNombre());
            genericDao.merge(cliente);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcepcionBaseDatos(e, "Error al actualizar cliente");
        }
    }

    public List<Cliente> listarClientes() throws GeneralException {
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            clientes.addAll(genericDao.listar(Cliente.class));
            Collections.sort(clientes, new Comparator<Cliente>() {

                public int compare(Cliente o1, Cliente o2) {
                    return o1.getNombre().compareTo(o2.getNombre());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcepcionBaseDatos(e, "Error al actualizar cliente");
        }
        finally {
            return clientes;
        }
    }
}
