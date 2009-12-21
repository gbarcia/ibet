package ve.edu.ucab.ibet.servicios.impl;

import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioTableroGanancia;

/**
 * Clase concreta de implementacion de servicios para TableroGanancia
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ServicioTableroGananciaImpl implements IServicioTableroGanancia {

    private IGenericDao genericDao;

    public IGenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(IGenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public void agregarTableroGanancia(TableroGanancia tablero) {
        if (tablero == null) {
            throw new ExcepcionNegocio("tableroganancia.invalido");
        }
        tablero.setEmpato(null);
        tablero.setGano(null);
        genericDao.insertar(tablero);
    }

    public void actualizarTableroGanancia(TableroGanancia tablero) {
        if (tablero == null) {
            throw new ExcepcionNegocio("tableroganancia.invalido");
        }
        genericDao.merge(tablero);
    }

}
