package ve.edu.ucab.ibet.servicios.interfaces;

import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;

/**
 * Contrato para el servicio del perfil del usuario
 * @author maya
 * @version 1.0
 */
public interface IServicioPerfilUsuario {

    /**
     * Metodo para obtener los datos de un usuario
     * @param username nombre de usuario
     * @return Users
     */
    public Users obtenerDatosUsuarioM(String username) throws GeneralException;
    public void actualizarDatosUsuarioM(Users user) throws GeneralException;

}
