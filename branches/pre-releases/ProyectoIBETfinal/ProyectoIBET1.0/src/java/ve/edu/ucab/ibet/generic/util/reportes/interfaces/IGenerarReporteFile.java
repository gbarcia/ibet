package ve.edu.ucab.ibet.generic.util.reportes.interfaces;

import java.util.Map;
import ve.edu.ucab.ibet.dominio.enums.TipoDocumentoReporte;

/**
 * Interfaz para la generacion de reportes en el disco duro
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IGenerarReporteFile {

    public void generarReporte(Map<String, Object> parameters, TipoDocumentoReporte tipo, String nombreReporte);
}
