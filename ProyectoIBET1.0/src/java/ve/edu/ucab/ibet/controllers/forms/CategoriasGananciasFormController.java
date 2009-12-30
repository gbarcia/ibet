package ve.edu.ucab.ibet.controllers.forms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataAccessException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.dominio.enums.TipoDocumentoReporte;
import ve.edu.ucab.ibet.dominio.to.forms.FechasTO;
import ve.edu.ucab.ibet.generic.util.UtilMethods;

/**
 * Clase para recoger los parametros que serviran para generar el reporte
 * de ganancias por categoria
 * @author maya
 * @version 1.0
 */
public class CategoriasGananciasFormController extends SimpleFormController {

    public CategoriasGananciasFormController() {
        setCommandClass(FechasTO.class);
        setCommandName("reporteGanancias");
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest req, HttpServletResponse resp, Object command, BindException errors) {
        FechasTO fechas = (FechasTO) command;
        String extReporte = (fechas.getTipoReporte() == TipoDocumentoReporte.PDF) ? ".pdf" : ".xls";
        ModelAndView mv = null;
        String agregado = (fechas.getTipoReporte() == TipoDocumentoReporte.PDF) ? "PDF" : "XLS";

        String fechaInicio = UtilMethods.fechaToString(fechas.getFechaInicio());
        String fechaFin = UtilMethods.fechaToString(fechas.getFechaFin());

        try {
            mv = new ModelAndView(new RedirectView("/ProyectoIBET/repCategoriasGanancias" + agregado + extReporte +
                    "?fechaInicio=" + fechaInicio + "&fechaFin=" + fechaFin));
        } catch (DataAccessException e) {
            mv = new ModelAndView("errorDA");
            e.printStackTrace();
        } finally {
            mv.addObject("fechaInicio", fechas.getFechaInicio());
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

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
