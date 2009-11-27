package ve.edu.ucab.ibet.servicios.interfaces;

import ve.edu.ucab.ibet.dominio.Apuesta;
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
    public void esValidaApuestaUsuario(Users usuario, TableroGanancia tablero);

    /**
     * Firma para procesar la transaccion de la apuesta
     * @param apuesta apuesta a realizar
     * @exception ExcepcionNegocio se dispara cuando no se cumplen las condiciones
     * @exception DataAccesException se dispara cuando hay problemas con el servicio
     * de base de datos
     */
    public void realizarApuesta(Apuesta apuesta);

    /**
     * Firma para armar el objeto apuesta para registrar desde el controller
     * @param idEvento id de el evento al que se deseea apostar
     * @param idParticipante el id del participante seleccionado
     * @param monto el monto que se desea apostar
     * @param usuario el usuario que realizara la apuesta
     * @return Objeto Apuesta con los datos completos para apostar
     */
    public Apuesta armarApuestaParaRealizar(String idEvento, String idParticipante,
            String monto, Users usuario);
}
