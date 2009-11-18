package ve.edu.ucab.ibet.servicios.interfaces;

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
    public void registroNuevoUsuarioM(Users user);

    /**
     * firma para verificar si existe un usuario
     * @param u objeto usuario a verificar
     * @return condicion booleana
     * @throws DataAccessException
     */
    public boolean existeUsuarioM(Users u);

    /**
     * firma para obtener los datos de Un usuario
     * @param username String del usuario a buscar
     * @return Objeto Usuario con los datos
     * @throws DataAccessException
     */
    public Users obtenerDatosUsuarioM(String username);

    /**
     * Firma para actualizar un usuario
     * @param user usuario a actualizar
     * @throws DataAccessException
     */
    public void actualizarDatosUsuarioM(Users user);

    /**
     * firma para obtener cual campo ha causado el error de negocio
     * @param mensaje mensaje de la excepcion de negocio
     * @return el nombre del field que causo el error
     */
    public String obtenerAtributoError(String mensaje);

    /**
     * Firma para convertir el objeto de transferencia del formulario
     * en el objeto de dominio Useres
     * @param to transfer object a convertir
     * @return objeto Users o null si to es nulo
     */
    public Users transferObjectToModel(RegistroUsuarioTO to);

    /**
     * Firma para confirmar el registro de un usuario
     * @param username el nombre nick-name del usuario a confirmar
     */
    public void confirmarRegistroUsuario (String username);

    /**
     * Firma para habilitar un usuario
     * @param username el nick-name del usuario a habilitar
     */
    public void habilitarUsuario (String username);

    /**
     * firma para deshabilitar un usuario registrado
     * @param username el nick-name del usuario a desabilitar
     */
    public void deshabilitarUsuario (String username);
}
