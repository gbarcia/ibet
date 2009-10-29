package ve.edu.ucab.ibet.servicios.impl;

import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.generic.excepciones.bd.ExcepcionBaseDatos;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioPerfilUsuario;

/**
 *
 * @author maya
 */
public class ServicioPerfilUsuarioImpl implements IServicioPerfilUsuario {

    private IGenericDao genericDao;
    private IHelperProperties hp;

    public IHelperProperties getHp() {
        return hp;
    }

    public void setHp(IHelperProperties hp) {
        this.hp = hp;
    }

    public IGenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(IGenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public Users obtenerDatosUsuarioM(String username) throws GeneralException {
        Users user = null;
        try {
            user = (Users) genericDao.findByPropertyUnique(Users.class, "username", username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcepcionBaseDatos(e, hp.getString("gpu.error.basededatos.obtenerusuarios"));
        } finally {
            return user;
        }

    }

    public void actualizarDatosUsuarioM(Users user) throws GeneralException {
        try {
            genericDao.modificar(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExcepcionBaseDatos(e, hp.getString("gpu.error.basededatos.actualizarusuarios"));
        }
    }
}
