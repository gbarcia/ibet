package ve.edu.ucab.ibet.servicios.impl.ws;

import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.dominio.to.ws.RespuestaProporcionWS;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioEvento;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ve.edu.ucab.ibet.dominio.Apuesta;
import ve.edu.ucab.ibet.dominio.MedioPago;
import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioApuesta;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioMedioPago;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 * Clase para ofrecer operaciones de logica de negocio a traves de un servicio web
 * @author Gerardo Barcia
 * @author Jonathan Trujillo
 * @author Mariale Uribe
 * @version 1.0
 */
@WebService(serviceName = "IbetWebServices")
public class IbetWebServices extends SpringBeanAutowiringSupport {

    @Autowired
    private IServicioEvento servicioEvento;
    @Autowired
    private IServicioUsuario servicioUsuario;
    @Autowired
    private IServicioApuesta servicioApuesta;
    @Autowired
    private IServicioMedioPago servicioMedioPago;
    private Logger log = Logger.getLogger(getClass());

    /**
     * Operacion de servicio web para consultar la proporcion que paga en un
     * determinado evento, cada participante suscrito al mismo
     * @param fecha fecha de ocurrencia del evento
     * @param equipoUno nombre del equipo Uno a participar en el evento
     * @param equipoDos nombre del equipo Dos a participar en el evento
     * @return Objeto de tipo RespuestaProporcionWS con la informacion de las
     * proporciones de pago por equipo o null en caso de no existir el evento
     */
    @WebMethod(operationName = "consultarProporcionEvento")
    public RespuestaProporcionWS consultarProporcionEvento(@WebParam(name = "fecha") String fecha, @WebParam(name = "equipoUno") String equipoUno,
            @WebParam(name = "equipoDos") String equipoDos) {
        RespuestaProporcionWS respuesta = new RespuestaProporcionWS();
        try {
            log.info("Iniciando operacion de web service para consultar proporcion de Evento");
            log.info("Datos recibidos: " + "-- fecha: " + fecha + " -- equipoUno: " + equipoUno +
                    "-- equipoDos: " + equipoDos);
            Date fechaEvento = UtilMethods.convertirFechaEnFormatoIbet(fecha);
            TableroGanancia tableroUno = servicioEvento.obtenerTableroPorEquipoyEvento(fechaEvento, equipoUno);
            TableroGanancia tableroDos = servicioEvento.obtenerTableroPorEquipoyEvento(fechaEvento, equipoDos);
            respuesta.setEquipoUno(tableroUno.getParticipante().getNombre());
            respuesta.setEquipoDos(tableroDos.getParticipante().getNombre());
            respuesta.setProporcionEquipoUno(tableroUno.getPropocionGano());
            respuesta.setProporcionEquipoDos(tableroDos.getPropocionGano());
            log.info("Datos enviados de vuelta: " + " --equipoUno: " + respuesta.getEquipoUno() +
                    " -- equipoDos: " + respuesta.getEquipoDos() + " --proporcionE1: " + respuesta.getProporcionEquipoUno() +
                    " -- proporcionE2: " + respuesta.getProporcionEquipoDos());
        } catch (ExcepcionNegocio en) {
            en.printStackTrace();
            log.error("Ocurrio un error de negocio en la operacion consultarProporcionEvento. Detalles:" +
                    en.getErrorCode() + " ==> errores: " + en.getMessage());
            respuesta = null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Ocurrio un error en la operacion consultarProporcionEvento." +
                    "==> errores: " + e.getMessage() + e.getStackTrace());
            respuesta = null;
        } finally {
            return respuesta;
        }
    }

    /**
     * operacion de servicio web para consultas de resultados de eventos
     * @param fecha fecha del evento en formato dd/mm/yyyy
     * @param participanteUno nombre del participante uno segun convencion
     * @param participanteDos nombre del participante dos segun convencion
     * @return Cadena con el resultado del evento, null en caso de no existir
     */
    @WebMethod(operationName = "consultarResultadoEvento")
    public String consultarResultadoEvento(@WebParam(name = "fecha") String fecha,
            @WebParam(name = "participanteUno") String participanteUno,
            @WebParam(name = "participanteDos") String participanteDos) {
        String respuesta = new String();
        try {
            log.info("Iniciando operacion de web service para consultarResultadoEvento ");
            log.info("Datos recibidos: " + "-- fecha: " + fecha + " -- equipoUno: " + participanteUno +
                    "-- equipoDos: " + participanteDos);
            Date fechaEvento = UtilMethods.convertirFechaEnFormatoIbet(fecha);
            TableroGanancia tableroUno = servicioEvento.obtenerTableroPorEquipoyEvento(fechaEvento, participanteUno);
            TableroGanancia tableroDos = servicioEvento.obtenerTableroPorEquipoyEvento(fechaEvento, participanteDos);
            if (tableroUno.getEvento().getResultado().equals(tableroDos.getEvento().getResultado())) {
                respuesta = tableroUno.getEvento().getResultado();
            }
            log.info("Resultado enviado: ==> " + respuesta);
        } catch (ExcepcionNegocio en) {
            en.printStackTrace();
            log.error("Ocurrio un error en la operacion consultarResultadoEvento. Detalles:" +
                    en.getErrorCode() + " ==> errores: " + en.getMessage());
            respuesta = null;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Ocurrio un error en la operacion consultarProporcionEvento." +
                    "==> errores: " + e.getMessage());
            respuesta = null;
        } finally {
            return respuesta;
        }
    }

    /**
     * operacion de servicio web para realizar una apuesta
     * @param idEvento identificador del evento a apostar
     * @param nombreEquipoApostado nombre del equipo por quien se apuesta
     * @param monto monto de la apuesta
     * @param nombreUsuario nombre del usuario que realiza la apuesta
     * @param passUsuario clave del usuario que realiza la apuesta
     * @param nombreMetodoPago nombre del metodo de pago asociado a la apuesta
     * @return valor booleano con la condicion de exito o fracaso de la operacion
     */
    @WebMethod(operationName = "realizarApuesta")
    public Boolean realizarApuesta(@WebParam(name = "idEvento") Integer idEvento,
            @WebParam(name = "nombreEquipoApostado") String nombreEquipoApostado,
            @WebParam(name = "monto") Double monto, @WebParam(name = "nombreUsuario") String nombreUsuario, @WebParam(name = "passUsuario") String passUsuario,
            @WebParam(name = "nombreMetodoPago") String nombreMetodoPago) {
        Boolean resultado = Boolean.TRUE;
        try {
            log.info("Iniciando operacion de web service para realizarApuesta ");
            log.info("Datos recibidos: " + "-- idEvento: " + idEvento + " -- NombreEquipoApostado: " + nombreEquipoApostado +
                    "-- montoApostado: " + monto + " -- nombreUsuario: " + nombreUsuario +
                    "-- metodoPago: " + nombreMetodoPago);
            Users usuario = servicioUsuario.comprobarValidezUsuario(nombreUsuario, passUsuario);
            Participante participanteApostado = servicioEvento.obtenerParticipantePorNombre(nombreEquipoApostado);
            MedioPago medioPago = servicioMedioPago.obtenerMedioPago(nombreMetodoPago);
            Apuesta apuesta = servicioApuesta.armarApuestaParaRealizar(idEvento.toString(),
                    participanteApostado.getId().toString(), monto.toString(), usuario);
            apuesta.setMedioPago(medioPago);
            servicioApuesta.realizarApuesta(apuesta);
        } catch (ExcepcionNegocio en) {
            en.printStackTrace();
            resultado = Boolean.FALSE;
            log.error("Ocurrio una excepcion de negocio durante la operacion ws para relizar apuesta: ==> " + en.getMessage());
        } catch (DataAccessException dae) {
            dae.printStackTrace();
            resultado = Boolean.FALSE;
            log.error("Ocurrio una excepcion de base de datos durante la operacion ws para relizar apuesta: ==> " + dae.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            resultado = Boolean.FALSE;
            log.error("Ocurrio una excepcion durante la operacion ws para relizar apuesta: ==> " + e.getMessage());
        } finally {
            return resultado;
        }
    }
}
