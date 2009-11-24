package ve.edu.ucab.ibet.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import ve.edu.ucab.ibet.dominio.Evento;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioEvento;

/**
 * Clase para ofrecer servicios de Evento
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ServicioEventoImpl implements IServicioEvento {

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
    public List<Evento> obtenerEventosDeUnaCategoria(Integer idSubcategoria) {
        List<Evento> eventos = new ArrayList<Evento>();
        String query = "select a from Categoria c inner join c.eventoCollection as a " +
                "where c.id=? " +
                "order by a.fechaEvento, a.hora";
        Object[] parametros = {idSubcategoria};
        eventos.addAll(genericDao.ejecutarQueryList(query, parametros));
        return eventos;
    }
}
