package ve.edu.ucab.ibet.servicios.interfaces;

import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;

/**
 * Contrato para ofrecer servicios de operaciones con Usuarios
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IServicioUsuario {

    /**
     * firma para el registro de un nuevo usuario
     * @param user objeto usuario a registrar
     * @return booleano con la condicion
     * @throws GeneralException
     */
    public boolean registroNuevoUsuarioM(Users user) throws GeneralException;

    /**
     * firma para verificar si existe un usuario
     * @param u objeto usuario a verificar
     * @return condicion booleana
     */
    public boolean existeUsuarioM(Users u);

    /**
     * firma para obtener los datos de Un usuario
     * @param username String del usuario a buscar
     * @return Objeto Usuario con los datos
     * @throws GeneralException
     */
    public Users obtenerDatosUsuarioM(String username) throws GeneralException;

    /**
     * Firma para actualizar un usuario
     * @param user usuario a actualizar
     * @throws GeneralException
     */
    public void actualizarDatosUsuarioM(Users user) throws GeneralException;
}
