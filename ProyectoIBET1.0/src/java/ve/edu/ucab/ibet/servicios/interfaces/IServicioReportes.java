package ve.edu.ucab.ibet.servicios.interfaces;

import java.util.Date;
import java.util.List;
import ve.edu.ucab.ibet.dominio.to.reportes.CategoriasGananciasTO;
import ve.edu.ucab.ibet.dominio.to.reportes.HistorialApuestasTO;

/**
 * Servicio para gestion de reportes
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IServicioReportes {

    public List<HistorialApuestasTO> reporteHistorialApuestas(String username, Date fechaInicio, Date fechaFin);
    
    public List<CategoriasGananciasTO> reporteCategoriasGanancias();

}
