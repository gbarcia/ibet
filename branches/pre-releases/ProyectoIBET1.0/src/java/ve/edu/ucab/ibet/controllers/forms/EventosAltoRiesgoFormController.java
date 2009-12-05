/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ve.edu.ucab.ibet.controllers.forms;

import org.springframework.web.servlet.mvc.SimpleFormController;
import ve.edu.ucab.ibet.dominio.to.forms.AltoRiesgoTO;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.dominio.enums.TipoDocumentoReporte;


/**
 *
 * @author maya
 */
public class EventosAltoRiesgoFormController extends SimpleFormController {

    public EventosAltoRiesgoFormController() {
        setCommandClass(AltoRiesgoTO.class);
        setCommandName("reporteEventosAltoRiesgo");
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest req, HttpServletResponse resp, Object command, BindException errors) {
        AltoRiesgoTO evento = (AltoRiesgoTO) command;
        String extReporte = (evento.getTipoReporte() == TipoDocumentoReporte.PDF) ? ".pdf" : ".xls";
        ModelAndView mv = null;
        String agregado = (evento.getTipoReporte() == TipoDocumentoReporte.PDF) ? "PDF" : "XLS";

        String monto = String.valueOf(evento.getMonto());
        
        try {
            mv = new ModelAndView(new RedirectView("/ProyectoIBET/repEventosRiesgo" + agregado + extReporte +
                    "?monto=" + monto));
        } catch (DataAccessException e) {
            mv = new ModelAndView("errorDA");
            e.printStackTrace();
        } finally {
            return mv;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request) {
        Map referenceData = new HashMap();
        referenceData.put("opcionTipoReporte", TipoDocumentoReporte.values());
        return referenceData;
    }

}
