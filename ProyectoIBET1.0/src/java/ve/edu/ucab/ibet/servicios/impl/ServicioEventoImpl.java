package ve.edu.ucab.ibet.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import ve.edu.ucab.ibet.dominio.Evento;
import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.Politica;
import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioEvento;

/**
 * Clase para ofrecer servicios de Evento
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ServicioEventoImpl implements IServicioEvento {

    private IGenericDao genericDao;
    private IHelperProperties helperProp;

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

    @SuppressWarnings("unchecked")
    public List<Evento> obtenerEventosDeUnaCategoria(Integer idSubcategoria) {
        List<Evento> eventos = new ArrayList<Evento>();
        String query = "select a from Categoria c inner join c.eventoCollection as a " +
                "where c.id=? " +
                "order by a.fechaEvento, a.hora";
        Object[] parametros = {idSubcategoria};
        eventos.addAll(genericDao.ejecutarQueryList(query, parametros));
        return eventos;
    }

    public Evento obtenerEventoporTableroGanancia(TableroGanancia tablero) {
        Evento resultado = (Evento) genericDao.findByPropertyUnique(Evento.class, "id", tablero.getTableroGananciaPK().getIdEvento());
        if (resultado == null) {
            throw new ExcepcionNegocio("errors.evento.noexiste");
        }
        return resultado;
    }

    public Politica obtenerPoliticaParaEvento(Evento evento) {
        Politica politica = null;
        String query = new String();
        Object[] parametros = {evento.getId()};
        query = "Select e.idPolitica from Evento e where e.id = ?";
        politica = (Politica) genericDao.ejecutarQueryUnique(query, parametros);
        if (politica == null) throw new ExcepcionNegocio("errors.evento.politica.noexiste");
        return politica;
    }

    public List<Evento> obtenerProximosEventos() {
        List<Evento> eventos = new ArrayList<Evento>();
        String query = "select a from Categoria c inner join c.eventoCollection as a " +
                "where a.fechaEvento >= current_date " +
                "and a.estatus = 1 " +
                "and a.finalizado = 0 " +
                "order by a.fechaEvento, a.hora";
        Object[] parametros = {};
        eventos.addAll(genericDao.ejecutarQueryList(query, parametros, 0, 25));
        return eventos;
    }

    public List<Evento> obtenerProximosEventosConImagen() {
        List<Evento> eventos = new ArrayList<Evento>();
        String query = "select a from Categoria c inner join c.eventoCollection as a " +
                "where a.fechaEvento >= current_date " +
                "and a.estatus = 1 " +
                "and a.finalizado = 0 " +
                "and a.imagenEvento is not null " +
                "order by a.fechaEvento, a.hora";
        Object[] parametros = {};
        eventos.addAll(genericDao.ejecutarQueryList(query, parametros, 0, 25));
        return eventos;
    }

    public Evento obtenerEvento(Integer idEvento) {
        Evento evento = null;
        evento = (Evento)genericDao.findByPropertyUnique(Evento.class, "id", idEvento);
        if (evento == null){
            throw new ExcepcionNegocio("errors.evento.noExiste");
        }
        return evento;
    }

    public Participante obtenerParticipante(Integer idParticipante) {
        Participante participante = null;
        participante = (Participante)genericDao.findByPropertyUnique(Participante.class, "id", idParticipante);
        if (participante == null){
            throw new ExcepcionNegocio("errors.evento.noExiste");
        }
        return participante;
    }
}
