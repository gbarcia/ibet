package ve.edu.ucab.ibet.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
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
        String[] propiedades = {"activo"};
        Object[] valores = {Boolean.TRUE};
        Integer[] operadores = {TipoOperadorBusqueda.EQUAL};

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

    public MedioPago obtenerMedioPagoId(Integer id){
        MedioPago medioPago = null;
        medioPago = (MedioPago) genericDao.findByPropertyUnique(MedioPago.class, "id", id);
        if(medioPago == null){
            throw new ExcepcionNegocio("mediopago.noexiste");
        }
        return medioPago;
    }

    public MedioPago obtenerMedioPago(String nombre) {
        MedioPago medioPago = new MedioPago();
        String query = new String();

        Object[] o = new Object[1];
        o[0] = nombre;

        query = "select mp from MedioPago mp where mp.nombre = ?";

        medioPago = (MedioPago) genericDao.ejecutarQueryUnique(query, o);

        return medioPago;
    }

    private Boolean existeMedioPago(MedioPago medioPago) {
        MedioPago pago = new MedioPago();
        Boolean existe = Boolean.TRUE;
        Object[] o = new Object[1];

        o[0] = medioPago.getNombre();

        String query = "select mp from MedioPago mp where mp.nombre = ? ";

        pago = (MedioPago) genericDao.ejecutarQueryUnique(query, o);

        if (pago == null) {
            return Boolean.FALSE;
        }

        return existe;
    }

    private Boolean existeMedioPagoPorId(MedioPago medioPago) {
        MedioPago pago = new MedioPago();
        Boolean existe = Boolean.TRUE;
        Object[] o = new Object[1];

        o[0] = medioPago.getId();

        String query = "select mp from MedioPago mp where mp.id = ? ";

        pago = (MedioPago) genericDao.ejecutarQueryUnique(query, o);

        if (pago == null) {
            return Boolean.FALSE;
        }

        return existe;
    }

    public void crearMedioPago(MedioPago medioPago) {
        if (!existeMedioPago(medioPago)) {
            Integer id = genericDao.getNextId(medioPago);
            medioPago.setId(id);
            medioPago.setActivo(Boolean.TRUE);
            genericDao.insertar(medioPago);
        } else {
            throw new ExcepcionNegocio("errors.mediopago.yaexiste");
        }
    }

    public void editarMedioPago(MedioPago medioPago) {
        if (existeMedioPagoPorId(medioPago)) {
            medioPago.setNombre(medioPago.getNombre());
            genericDao.limpiar();
            genericDao.merge(medioPago);
        } else {
            throw new ExcepcionNegocio("errors.mediopago.noexiste");
        }
    }

    public void inhabilitarMedioPago(MedioPago medioPago) {
        if (existeMedioPagoPorId(medioPago)) {
            if (medioPago.getActivo()) {
                medioPago.setActivo(Boolean.FALSE);
                genericDao.limpiar();
                genericDao.merge(medioPago);
            } else {
                throw new ExcepcionNegocio("errors.mediopago.yaestainhabilitado");
            }
        } else {
            throw new ExcepcionNegocio("errors.mediopago.noexiste");
        }
    }

    public void habilitarMedioPago(MedioPago medioPago) {
        if (existeMedioPagoPorId(medioPago)) {
            if (!medioPago.getActivo()) {
                medioPago.setActivo(Boolean.TRUE);
                genericDao.limpiar();
                genericDao.merge(medioPago);
            } else {
                throw new ExcepcionNegocio("errors.mediopago.yaestahabilitado");
            }
        } else {
            throw new ExcepcionNegocio("errors.mediopago.noexiste");
        }
    }

    @SuppressWarnings("unchecked")
    public List<MedioPago> listarMedioPagos() {
        List<MedioPago> medioPagos = new ArrayList<MedioPago>();
        medioPagos = genericDao.listar(MedioPago.class);
        return medioPagos;
    }
}
