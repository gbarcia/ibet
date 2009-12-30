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

/**
 * Clase para recoger a traves de un formulario los parametros necesarios para
 * generar el reporte de usuarios con mayor aciertos
 * @author maya
 * @version 1.0
 */
public class UsuariosMayorAciertosFormController extends SimpleFormController  {

    public UsuariosMayorAciertosFormController() {
        setCommandClass(FechasTO.class);
        setCommandName("reporteUsuariosMayorAciertos");
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest req, HttpServletResponse resp, Object command, BindException errors) {
        FechasTO documento = (FechasTO) command;
        String extReporte = (documento.getTipoReporte() == TipoDocumentoReporte.PDF) ? ".pdf" : ".xls";
        ModelAndView mv = null;
        String agregado = (documento.getTipoReporte() == TipoDocumentoReporte.PDF) ? "PDF" : "XLS";

        try {
            mv = new ModelAndView(new RedirectView("/ProyectoIBET/repUsuariosMayorAciertos" + agregado + extReporte ));
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

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
