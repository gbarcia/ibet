package ve.edu.ucab.ibet.servicios.interfaces;

import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;

/**
 * Contrato para ofrecer servicios de operaciones con Usuarios
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IServicioUsuario {

    public boolean  registroNuevoUsuarioM (Users user) throws GeneralException;

}
