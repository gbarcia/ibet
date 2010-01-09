package ve.edu.ucab.ibet.controllers.views.gestioneventos;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.DataAccessException;
import ve.edu.ucab.ibet.dominio.Evento;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;
import ve.edu.ucab.ibet.controllers.forms.validator.RegistroEventoValidator;
import ve.edu.ucab.ibet.dominio.Categoria;
import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.Politica;
import ve.edu.ucab.ibet.dominio.ProporcionPago;
import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.dominio.to.forms.RegistroEventoTO;
import ve.edu.ucab.ibet.generic.excepciones.GeneralException;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioCategoria;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioEvento;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioTableroGanancia;
import ve.edu.ucab.ibet.servicios.interfaces.estrategias.EstrategiaProporcion;

/**
 * Clase para almacenar y procesar el formulario de registro de eventos
 * @author Gerardo Barcia
 * @version 1.0
 */
public class RegistroEventoFormController extends AbstractWizardFormController {

    private IServicioEvento servicioEvento;
    private IServicioTableroGanancia servicioTableroGanancia;
    private IServicioCategoria servicioCategoria;
    private IHelperProperties helperProperties;
    private String nombreCategoria;
    private Participante participanteUno;
    private Participante participanteDos;
    private EstrategiaProporcion estrategia;

    public RegistroEventoFormController() {
        setCommandClass(RegistroEventoTO.class);
        setCommandName("registroEvento");
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        RegistroEventoTO registro = new RegistroEventoTO();
        registro.setCategoria(new Categoria());
        Politica politica = new Politica();
        politica.setMontoMaximo(100000.0);
        registro.setPolitica(politica);
        registro.setParticipanteUno(new Participante());
        registro.setParticipanteDos(new Participante());
        registro.setTableroGananciaUno(new TableroGanancia());
        registro.setTableroGananciaDos(new TableroGanancia());
        return registro;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Map referenceData(HttpServletRequest request, int page) throws Exception {
        Map referencia = new HashMap();
        if (page == 0) {
            List<String> listaCategoriaMap = new ArrayList<String>();
            for (Categoria c : servicioCategoria.obtenerCategoriasHijos()) {
                listaCategoriaMap.add(c.getNombre());
            }
            Map<Boolean, String> finAntes = new HashMap<Boolean, String>();
            finAntes.put(Boolean.TRUE, "SI");
            finAntes.put(Boolean.FALSE, "NO");
            referencia.put("listaCategorias", listaCategoriaMap);
            referencia.put("finAntes", finAntes);
        } else if (page == 1) {
            Map<Integer, String> listaParticipantesMap = new HashMap<Integer, String>();
            for (Participante p : servicioTableroGanancia.obtenerParticipantesPorCategoria(nombreCategoria)) {
                listaParticipantesMap.put(p.getId(), p.getNombre());
            }
            referencia.put("listaParticipantes", listaParticipantesMap);
        } else if (page == 2) {
            ProporcionPago proporcion = new ProporcionPago();
            Categoria categoria = servicioCategoria.obtenerCategoriaPorNombre(nombreCategoria);
            if (categoria.getLogicaAutomatica()) {
                estrategia = (EstrategiaProporcion) getApplicationContext().getBean(categoria.getNombreLogica());
                proporcion = estrategia.fijarProporcion(participanteUno, participanteDos);
            }
            referencia.put("empate", categoria.getEmpate());
            referencia.put("logica", categoria.getLogicaAutomatica());
            referencia.put("propEquipoUno", proporcion.getPagoPorGanar());
            referencia.put("propEquipoDos", proporcion.getPagoPorPerder());
            referencia.put("propEmpate", proporcion.getPagoPorEmpate());
        }
        return referencia;
    }

    @Override
    protected void postProcessPage(HttpServletRequest request, Object command, Errors errors, int page) throws Exception {
        if (page == 0) {
            RegistroEventoTO registro = (RegistroEventoTO) command;
            nombreCategoria = registro.getCategoria().getNombre();
        }
        if (page == 1) {
            RegistroEventoTO registro = (RegistroEventoTO) command;
            participanteUno = registro.getParticipanteUno();
            participanteDos = registro.getParticipanteDos();
        }
    }

    @Override
    protected ModelAndView processCancel(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        List<Evento> listaEventos = servicioEvento.todosLosEventos();
        ModelAndView mv = new ModelAndView(new RedirectView(request.getContextPath() + "/privado/back/admin.htm"));
        mv.addObject("listaEventos", listaEventos);
        return mv;
    }

    @Override
    protected ModelAndView processFinish(HttpServletRequest req, HttpServletResponse resp, Object command, BindException errors) throws Exception {
        Boolean resultado = Boolean.FALSE;
        String mensaje = "";
        RegistroEventoTO registroEvento = new RegistroEventoTO();
        registroEvento = (RegistroEventoTO) command;
        Categoria categoria = servicioCategoria.obtenerCategoriaPorNombre(nombreCategoria);
        registroEvento.setCategoria(categoria);
        registroEvento.getTableroGananciaUno().setParticipante(registroEvento.getParticipanteUno());
        registroEvento.getTableroGananciaDos().setParticipante(registroEvento.getParticipanteDos());
        registroEvento.getTableroGananciaDos().setProporcionEmpate(registroEvento.getTableroGananciaUno().getProporcionEmpate());
        Evento evento = servicioEvento.transferObjectToEvento(registroEvento);
        ModelAndView mv = new ModelAndView(new RedirectView(req.getContextPath() + "/privado/back/admin.htm"));
        List<Evento> listaEventos = servicioEvento.todosLosEventos();
        mv.addObject("listaEventos", listaEventos);
        try {
            servicioEvento.agregarEvento(evento, registroEvento.getTableroGananciaUno(), registroEvento.getTableroGananciaDos());
            if (!registroEvento.getImagenEvento().isEmpty()) {
                File f = new File(helperProperties.getString("directorio.imagenes.eventos") + registroEvento.getImagenEvento().getOriginalFilename());
                registroEvento.getImagenEvento().transferTo(f);
            }
            resultado = Boolean.TRUE;
            mensaje = "El evento " + registroEvento.getNombreEvento() + " se ha registrado con exito";
        } catch (DataAccessException e) {
            mensaje = "El sistema no se encuentra disponible. Intente de nuevo mas tarde";
            e.printStackTrace();
        } catch (GeneralException e) {
            e.printStackTrace();
            errors.rejectValue(null, e.getKeyError());
            mv = showForm(req, resp, errors);
        } catch (Exception e) {
            e.printStackTrace();
            errors.rejectValue(null, "error.apuesta.general");
            mv = showForm(req, resp, errors);
        } finally {
            if (!mensaje.equals("")) {
                mv.addObject("resultado", resultado);
                mv.addObject("mensaje", mensaje);
            }
            return mv;
        }
    }

    @Override
    protected void validatePage(Object command, Errors errors, int page) {
        RegistroEventoValidator validator = (RegistroEventoValidator) getValidator();
        switch (page) {
            case 0:
                validator.validarPaginaUno(command, errors);
                break;
            case 1:
                validator.validarPaginaDos(command, errors);
                break;
            case 2:
                RegistroEventoTO registro = (RegistroEventoTO) command;
                registro.setCategoria(servicioCategoria.obtenerCategoriaPorNombre(nombreCategoria));
                command = registro;
                validator.validarPaginaTres(command, errors);
                break;
            default:
                break;
        }
    }

    @Override
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    public IServicioEvento getServicioEvento() {
        return servicioEvento;
    }

    public void setServicioEvento(IServicioEvento servicioEvento) {
        this.servicioEvento = servicioEvento;
    }

    public IServicioTableroGanancia getServicioTableroGanancia() {
        return servicioTableroGanancia;
    }

    public void setServicioTableroGanancia(IServicioTableroGanancia servicioTableroGanancia) {
        this.servicioTableroGanancia = servicioTableroGanancia;
    }

    public IServicioCategoria getServicioCategoria() {
        return servicioCategoria;
    }

    public void setServicioCategoria(IServicioCategoria servicioCategoria) {
        this.servicioCategoria = servicioCategoria;
    }

    public IHelperProperties getHelperProperties() {
        return helperProperties;
    }

    public void setHelperProperties(IHelperProperties helperProperties) {
        this.helperProperties = helperProperties;
    }
}
