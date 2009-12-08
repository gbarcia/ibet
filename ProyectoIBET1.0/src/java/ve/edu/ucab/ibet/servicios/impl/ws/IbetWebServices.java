package ve.edu.ucab.ibet.servicios.impl.ws;

import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.dominio.to.ws.RespuestaProporcionWS;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioEvento;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;

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
    private Logger log = Logger.getLogger(getClass());

    /**
     * Operacion de servicio web para consultar la proporcion que paga en un
     * determinado evento, cada participante suscrito al mismo
     * @param fecha fecha de ocurrencia del evento
     * @param equipoUno nombre del equipo Uno a participar en el evento
     * @param equipoDos nombre del equipo Dos a participar en el evento
     * @return Objeto de tipo RespuestaProporcionWS con la informacion de las
     * proporciones de pago por equipo
     */
    @WebMethod(operationName = "consultarProporcionEvento")
    public RespuestaProporcionWS consultarProporcionEvento(@WebParam(name = "fecha") String fecha, @WebParam(name = "equipoUno") String equipoUno, @WebParam(name = "equipoDos") String equipoDos) {
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
            log.error("Ocurrio un error en la operacion consultarProporcionEvento. Detalles:" +
                    en.getErrorCode() + " ==> errores: " + en.getMessage());
            respuesta = null;
        } finally {
            return respuesta;
        }
    }
}
