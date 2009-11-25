package ve.edu.ucab.ibet.servicios.interfaces;

import java.util.Date;
import java.util.List;
import ve.edu.ucab.ibet.dominio.Categoria;
import ve.edu.ucab.ibet.dominio.to.reportes.CantidadUsuariosCategoriaTO;
import ve.edu.ucab.ibet.dominio.to.reportes.CategoriasGananciaPerdidaTO;
import ve.edu.ucab.ibet.dominio.to.reportes.CategoriasPerdidasTO;
import ve.edu.ucab.ibet.dominio.to.reportes.EventosAltoRiesgoTO;
import ve.edu.ucab.ibet.dominio.to.reportes.HistorialApuestasTO;
import ve.edu.ucab.ibet.dominio.to.reportes.UsuariosMayorAciertosTO;

/**
 * Servicio para gestion de reportes
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IServicioReportes {

    /**
     * Firma para la realizacion del reporte de historial de apuestas
     * @param username Nombre de usuario al que se realizara el historial de apuestas
     * @param fechaInicio Fecha inicial para consultar el historial de apuestas
     * @param fechaFin Fecha final para consultar el historial de apuestas
     * @return Lista HistorialApuestasTO que contiene el resultado de la consulta
     */
    public List<HistorialApuestasTO> reporteHistorialApuestas(String username, Date fechaInicio, Date fechaFin);

    /**
     * Firma para la realizacion del reporte de ganancias por categoria
     * @param fechaInicio Fecha inicial para consultar las ganancias que existen por cada categoria
     * @param fechaFin Fecha final para consultar las ganancias que existen por cada categoria
     * @return Lista CategoriasGananciaPerdidaTO que contiene el resultado de la consulta
     */
    public List<CategoriasGananciaPerdidaTO> reporteCategoriasGanancias(Date fechaInicio, Date fechaFin);

    /**
     * Firma para la realizacion del reporte de perdidas por categoria
     * @param fechaInicio Fecha inicial para consultar las perdidas que existen por cada categoria
     * @param fechaFin Fecha Final para consultar las perdidas que existen por cada categoria
     * @return Lista CategoriasGananciaPerdidaTO que contiene el resultado de la consulta
     */
    public List<CategoriasGananciaPerdidaTO> reporteCategoriasPerdidas(Date fechaInicio, Date fechaFin);

    /**
     * Firma para listar las perdidas por cada categoria
     * @param perdidas Lista de CategoriasPerdidasTO
     * @return Lista CategoriaGananciaPerdidaTO que contiene el resultado de la consulta
     */
    public List<CategoriasGananciaPerdidaTO> listarPerdidasCategorias(List<CategoriasPerdidasTO> perdidas);

    /**
     * Firma para listar todas las categorias
     * @return Lista de tipo Categoria con el resultado de la consulta
     */
    public List<Categoria> listarCategorias();

    /**
     * Firma para la realizacion del reporte de cantidad de usuarios por categoria
     * @return Lista CantidadUsuariosCategoriaTO que contiene el resultado de la consulta
     */
    public List<CantidadUsuariosCategoriaTO> reporteCantidadUsuariosCategoria();

    /**
     * Firma para la realizacion del reporte de eventos de alto riesgo
     * @param monto Monto de alto riesgo
     * @return Lista de EventosAltoRiesgoTO que contiene el resultado de la consulta
     */
    public List<EventosAltoRiesgoTO> reporteEventosAltoRiesgo(Double monto);
    
    /**
     * Firma para listar los eventos de alto riesgo 
     * @param eventos Lista de EventosAltoRiesgoTO 
     * @param monto Monto del alto riesgo
     * @return Lista de EventosAltoRiesgoTO que contiene el resultado de la consulta
     */
    public List<EventosAltoRiesgoTO> listarEventosAltoRiesgo(List<EventosAltoRiesgoTO> eventos, Double monto);

    /**
     * Firma para la realizacion del reporte de Usuarios con mayor aciertos
     * @return Lista UsuariosMayorAciertosTO que contiene el resultado de la consulta
     */
    public List<UsuariosMayorAciertosTO> reporteUsuariosMayorAciertos();

}
