package ve.edu.ucab.ibet.servicios.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.security.annotation.Secured;
import ve.edu.ucab.ibet.dominio.to.reportes.CategoriasGananciasTO;
import ve.edu.ucab.ibet.dominio.to.reportes.HistorialApuestasTO;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioReportes;

/**
 * Servicio para obtener y gestionar reportes
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ServicioReportesImpl implements IServicioReportes {

    private IGenericDao genericDao;
    private IHelperProperties helperProp;

    @SuppressWarnings("unchecked")
    @Secured({"ROLE_USER"})
    public List<HistorialApuestasTO> reporteHistorialApuestas(String username, Date fechaInicio, Date fechaFin) {


        List<HistorialApuestasTO> historial = new ArrayList<HistorialApuestasTO>();

        Object[] o = new Object[3];
        String query = new String();

        o[0] = username;
        o[1] = fechaInicio;
        o[2] = fechaFin;

        query = "select New ve.edu.ucab.ibet.dominio.to.reportes.HistorialApuestasTO (u.username, a.fecha, a.monto, e.nombre, e.fechaEvento, e.resultado, p.nombre) " +
                "from Users u, Apuesta a, Categoria c, Evento e, Participante p, TableroGanancia tg " +
                "where u.username = a.apuestaPK.username " +
                "and c.id = e.idCategoria " +
                "and e.id = tg.tableroGananciaPK.idEvento " +
                "and p.id  = tg.tableroGananciaPK.idParticipante " +
                "and tg.tableroGananciaPK.idEvento = a.tableroGanancia.evento.id " +
                "and tg.tableroGananciaPK.idParticipante = a.tableroGanancia.participante.id " +
                "and u.username = ? " +
                "and a.fecha between ? and ? " +
                "and e.finalizado = true ";

        historial.addAll(genericDao.ejecutarQueryList(query, o));

        return historial;
    }

    @Secured({"ROLE_ADMIN"})
    public List<CategoriasGananciasTO> reporteCategoriasGanancias() {
        List<CategoriasGananciasTO> ganancias = new ArrayList<CategoriasGananciasTO>();

        String query = new String();

        query = "select New ve.edu.ucab.ibet.dominio.to.reportes.CategoriasGananciasTO (sum(a.monto), c.nombre) " +
                "from Categoria c, Evento e, TableroGanancia tg, Apuesta a, Users u, Participante p " +
                "where c.id = e.idCategoria " +
                "and e.id = tg.tableroGananciaPK.idEvento " +
                "and p.id = tg.tableroGananciaPK.idParticipante " +
                "and tg.tableroGananciaPK.idEvento = a.tableroGanancia.evento.id " +
                "and tg.tableroGananciaPK.idParticipante = a.tableroGanancia.participante.id " +
                "and u.username = a.users.username " +
                "and a.ganador = false " +
                "group by c.nombre";

        ganancias.addAll(genericDao.ejecutarQueryList(query));

        return ganancias;
    }

    public IGenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(IGenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public IHelperProperties getHelperProp() {
        return helperProp;
    }

    public void setHelperProp(IHelperProperties helperProp) {
        this.helperProp = helperProp;
    }
}
