package ve.edu.ucab.ibet.servicios.interfaces;

import org.springframework.security.annotation.Secured;
import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.dominio.Users;

/**
 * Servicio para la gestion de apuestas
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IServicioApuesta {
    /**
     * Firma para verificar que el usuario pueda apostar en ese evento
     * Las condiciones son:
     * Debe tener almenos algun metodo de pago asociado y activo
     * No ha apostado aun en este evento
     * @param usuario el objeto usuario que realizara la apuesta
     * @param tablero el objeto de tablero por el que aposto
     * @exception ExcepcionNegocio se dispara cuando no se cumplen las condiciones
     * @exception DataAccesException se dispara cuando hay problemas con el servicio
     * de base de datos
     */
    @Secured({"ROLE_USER"})
    public void esValidaApuestaUsuario (Users usuario, TableroGanancia tablero);

    /**
     * Firma para procesar la transaccion de la apuesta
     * @param usuario el usuario que va a realizar la apuesta
     * @param tablero el tablero por el cual aposto el usuario
     * @exception ExcepcionNegocio se dispara cuando no se cumplen las condiciones
     * @exception DataAccesException se dispara cuando hay problemas con el servicio
     * de base de datos
     */
    @Secured({"ROLE_USER"})
    public void realizarApuesta (Users usuario, TableroGanancia tablero);
}
