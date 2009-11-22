package ve.edu.ucab.ibet.servicios.interfaces;

import java.util.Date;
import java.util.List;
import ve.edu.ucab.ibet.dominio.Categoria;
import ve.edu.ucab.ibet.dominio.to.reportes.CategoriasGananciaPerdidaTO;
import ve.edu.ucab.ibet.dominio.to.reportes.CategoriasPerdidasTO;
import ve.edu.ucab.ibet.dominio.to.reportes.HistorialApuestasTO;

/**
 * Servicio para gestion de reportes
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IServicioReportes {

    public List<HistorialApuestasTO> reporteHistorialApuestas(String username, Date fechaInicio, Date fechaFin);
    
    public List<CategoriasGananciaPerdidaTO> reporteCategoriasGanancias();

    public List<CategoriasGananciaPerdidaTO> reporteCategoriasPerdidas();

    public List<CategoriasGananciaPerdidaTO> listarPerdidasCategorias(List<CategoriasPerdidasTO> perdidas);

    public List<Categoria> listarCategorias();

}
