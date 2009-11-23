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

    public List<HistorialApuestasTO> reporteHistorialApuestas(String username, Date fechaInicio, Date fechaFin);
    
    public List<CategoriasGananciaPerdidaTO> reporteCategoriasGanancias(Date fechaInicio, Date fechaFin);

    public List<CategoriasGananciaPerdidaTO> reporteCategoriasPerdidas(Date fechaInicio, Date fechaFin);

    public List<CategoriasGananciaPerdidaTO> listarPerdidasCategorias(List<CategoriasPerdidasTO> perdidas);

    public List<Categoria> listarCategorias();

    public List<CantidadUsuariosCategoriaTO> reporteCantidadUsuariosCategoria();

    public List<EventosAltoRiesgoTO> reporteEventosAltoRiesgo(Double monto);

    public List<EventosAltoRiesgoTO> listarEventosAltoRiesgo(List<EventosAltoRiesgoTO> eventos, Double monto);

    public List<UsuariosMayorAciertosTO> reporteUsuariosMayorAciertos();

}
