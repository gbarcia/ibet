package ve.edu.ucab.ibet.servicios.impl;

import java.util.ArrayList;
import java.util.List;
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

    public List<HistorialApuestasTO> reporteHistorialApuestas(String username) throws Exception {
        List<HistorialApuestasTO> historial = new ArrayList<HistorialApuestasTO>();

        Object[] o = new Object[1];
        String query = new String();

        o[0] = username;

        try {
            query = "select u.username, a.fecha, a.monto, e.nombre, e.fecha, e.resultado, p.nombre " +
                    "from Users u, Apuesta a, Categoria c, Evento e, Participante p, TableroGanancia tg " +
                    "where u.username = a.apuestaPK.username " +
                    "and c.id = e.idCategoria " +
                    "and e.id = tg.tableroGananciaPK.idEvento " +
                    "and p.id  = tg.tableroGananciaPK.idParticipante " +
                    "and tg.tableroGananciaPK.idEvento = a.tableroGanancia.evento.id " +
                    "and tg.tableroGananciaPK.idParticipante = a.tableroGanancia.participante.id " +
                    "and u.username = ? ";
            historial.addAll(genericDao.ejecutarQueryList(query, o));
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return historial;
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
