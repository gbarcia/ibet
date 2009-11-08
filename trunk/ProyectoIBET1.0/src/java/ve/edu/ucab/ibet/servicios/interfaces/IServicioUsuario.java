package ve.edu.ucab.ibet.servicios.interfaces;

import org.springframework.dao.DataAccessException;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.to.forms.RegistroUsuarioTO;

/**
 * Contrato para ofrecer servicios de operaciones con Usuarios
 * @author Gerardo Barcia
 * @version 2.0
 */
public interface IServicioUsuario {

    /**
     * firma para el registro de un nuevo usuario
     * @param user objeto usuario a registrar
     * @return booleano con la condicion
     * @throws DataAccessException
     */
    public void registroNuevoUsuarioM(Users user) throws DataAccessException;

    /**
     * firma para verificar si existe un usuario
     * @param u objeto usuario a verificar
     * @return condicion booleana
     * @throws DataAccessException
     */
    public boolean existeUsuarioM(Users u) throws DataAccessException;

    /**
     * firma para obtener los datos de Un usuario
     * @param username String del usuario a buscar
     * @return Objeto Usuario con los datos
     * @throws DataAccessException
     */
    public Users obtenerDatosUsuarioM(String username) throws DataAccessException;

    /**
     * Firma para actualizar un usuario
     * @param user usuario a actualizar
     * @throws DataAccessException
     */
    public void actualizarDatosUsuarioM(Users user) throws DataAccessException;

    /**
     * Firma para convertir el objeto de transferencia del formulario
     * en el objeto de dominio Useres
     * @param to transfer object a convertir
     * @return objeto Users o null si to es nulo
     */
    public Users transferObjectToModel(RegistroUsuarioTO to);
}
