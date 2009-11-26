package ve.edu.ucab.ibet.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.util.TipoOperadorBusqueda;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioMedioPago;

/**
 * Clase para ofrecer servicios de medios de pago
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ServicioMedioPagoImpl implements IServicioMedioPago {

    private IGenericDao genericDao;
    private IHelperProperties helperProp;

    public IGenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(IGenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public IHelperProperties getHelperProp() {
        return helperProp;
    }

    public void setHelperProp(IHelperProperties helperProp) {
        this.helperProp = helperProp;
    }

    @SuppressWarnings("unchecked")
    public List<MedioPago> obtenerMediosPagoVigentes() {
        List<MedioPago> lista = null;
        String [] propiedades = {"activo"};
        Object [] valores     = {Boolean.TRUE };
        Integer [] operadores = {TipoOperadorBusqueda.EQUAL};

        lista = genericDao.findByProperties(MedioPago.class, propiedades, valores, operadores);
        return lista;
    }

    public List<String> obtenerNombresMediosPagoVigentes(List<UsuarioMedioPago> lista) {
        List<String> listarResultado = new ArrayList<String>();
        for (UsuarioMedioPago medio : lista) {
            listarResultado.add(medio.getMedioPago().getNombre());
        }
        return listarResultado;
    }
}
