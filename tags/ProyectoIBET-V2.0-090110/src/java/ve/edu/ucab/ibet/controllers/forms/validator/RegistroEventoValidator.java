package ve.edu.ucab.ibet.controllers.forms.validator;

import java.util.Date;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ve.edu.ucab.ibet.dominio.to.forms.RegistroEventoTO;

/**
 * Clase de implementacion para la validacion de registro de un evento
 * @author Gerardo Barcia
 * @version 1.0
 */
public class RegistroEventoValidator implements Validator {

    public boolean supports(Class clazz) {
        return RegistroEventoTO.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        validarPaginaUno(target, errors);
        validarPaginaDos(target, errors);
        validarPaginaTres(target, errors);
    }

    public void validarPaginaUno(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "horaMax", "required.horaMax",
                "La hora maxima es requerida");
    }

    public void validarPaginaDos(Object target, Errors errors) {
        RegistroEventoTO registro = (RegistroEventoTO) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "horaEvento", "required.horaEvento",
                "La hora es requerida");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombreEvento", "required.nombreEvento",
                "El nombre del evento es requerido");
        validarParticipantes(registro.getParticipanteUno().getId(), registro.getParticipanteDos().getId(), errors);
        validarFechas(registro.getFechaEvento(), registro.getFechaMax(), errors);
    }

    public void validarPaginaTres(Object target, Errors errors) {
        RegistroEventoTO registro = (RegistroEventoTO) target;
        validarMonto(registro.getPolitica().getMontoMaximo(), "politica.montoMaximo", errors);
        validarMonto(registro.getTableroGananciaUno().getPropocionGano(), "tableroGananciaUno.propocionGano", errors);
        validarMonto(registro.getTableroGananciaDos().getPropocionGano(), "tableroGananciaDos.propocionGano", errors);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "politica.montoMaximo", "required.politica.MontoMaximo", "El monto maximo es requerido");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tableroGananciaUno.propocionGano", "required.tableroGananciaUno.proporcionGano", "La proporcion de ganar es requerida");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tableroGananciaDos.propocionGano", "required.tableroGananciaDos.proporcionGano", "La proporcion de ganar es requerida");
        System.out.println(registro.getCategoria().getEmpate());
        if (registro.getCategoria().getEmpate()) {
            validarMonto(registro.getTableroGananciaUno().getProporcionEmpate(), "tableroGananciaUno.proporcionEmpate", errors);
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tableroGananciaUno.proporcionEmpate", "required.tableroGananciaUno.proporcionEmpate", "La proporcion de empate es requerida");
        }
    }

    private void validarParticipantes(Integer idP1, Integer idP2, Errors errors) {
        if (idP1.equals(idP2)) {
            errors.reject("invalid.participanteUno.id", "Debe seleccionar participantes diferentes");
            errors.rejectValue("participanteUno.id", "errors.evento.participante");
        }
    }

    private void validarFechas(Date fechaEvento, Date fechaMaxApuesta, Errors errors) {
        if (fechaEvento != null && fechaMaxApuesta !=null) {
        if (fechaEvento.before(fechaMaxApuesta)) {
            errors.rejectValue("fechaEvento", "errors.evento.fecha");
        }
        }
    }

    private void validarMonto(Double monto, String campo, Errors errors) {
        if (monto != null) {
        if (monto <= 0 || monto.isNaN()) {
            errors.rejectValue(campo, "errors.evento.monto");
        }
        }
    }
}
