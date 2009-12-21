package ve.edu.ucab.ibet.servicios.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ve.edu.ucab.ibet.dominio.Apuesta;
import ve.edu.ucab.ibet.dominio.Evento;
import ve.edu.ucab.ibet.dominio.Participante;
import ve.edu.ucab.ibet.dominio.Politica;
import ve.edu.ucab.ibet.dominio.TableroGanancia;
import ve.edu.ucab.ibet.dominio.TableroGananciaPK;
import ve.edu.ucab.ibet.dominio.to.ws.RespuestaProporcionWS;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioEvento;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioTableroGanancia;

/**
 * Clase para ofrecer servicios de Evento
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ServicioEventoImpl implements IServicioEvento {

    private IGenericDao genericDao;
    private IHelperProperties helperProp;
    private IServicioTableroGanancia servicioTableroGanancia;

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

    public IServicioTableroGanancia getServicioTableroGanancia() {
        return servicioTableroGanancia;
    }

    public void setServicioTableroGanancia(IServicioTableroGanancia servicioTableroGanancia) {
        this.servicioTableroGanancia = servicioTableroGanancia;
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
        if (politica == null) {
            throw new ExcepcionNegocio("errors.evento.politica.noexiste");
        }
        return politica;
    }
    static Integer PROXIMOS_EVENTOS_MINIMO = 0;
    static Integer PROXIMOS_EVENTOS_MAXIMO = 25;

    @SuppressWarnings("unchecked")
    public List<Evento> obtenerProximosEventos() {
        List<Evento> eventos = new ArrayList<Evento>();
        String query = "select a from Categoria c inner join c.eventoCollection as a " +
                "where a.fechaEvento >= current_date " +
                "and a.estatus = 1 " +
                "and a.finalizado = 0 " +
                "order by a.fechaEvento, a.hora";
        Object[] parametros = {};
        eventos.addAll(genericDao.ejecutarQueryList(query, parametros, PROXIMOS_EVENTOS_MINIMO, PROXIMOS_EVENTOS_MAXIMO));
        return eventos;
    }

    @SuppressWarnings("unchecked")
    public List<Evento> obtenerProximosEventosDeUnaCategoria(Integer idSubcategoria) {
        List<Evento> eventos = new ArrayList<Evento>();
        String query = "select a from Categoria c inner join c.eventoCollection as a " +
                "where c.id=? " +
                "and a.fechaEvento >= current_date " +
                "and a.estatus = 1 " +
                "and a.finalizado = 0 " +
                "order by a.fechaEvento, a.hora";
        Object[] parametros = {idSubcategoria};
        eventos.addAll(genericDao.ejecutarQueryList(query, parametros));
        return eventos;
    }

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    public Evento obtenerEvento(Integer idEvento) {
        Evento evento = null;
        evento = (Evento) genericDao.findByPropertyUnique(Evento.class, "id", idEvento);
        if (evento == null) {
            throw new ExcepcionNegocio("errors.evento.noExiste");
        }
        return evento;
    }

    @SuppressWarnings("unchecked")
    private List<TableroGanancia> obtenerTableroGanancia(Integer idEvento) {
        List<TableroGanancia> tableroGanancia = new ArrayList<TableroGanancia>();
        Object[] o = new Object[1];
        o[0] = idEvento;

        String query = "select tg from " +
                "TableroGanancia tg " +
                "where tg.tableroGananciaPK.idEvento = ? ";
        tableroGanancia.addAll(genericDao.ejecutarQueryList(query, o));

        if (tableroGanancia == null) {
            throw new ExcepcionNegocio("errors.tableroganancia.noExiste");
        }
        return tableroGanancia;
    }

    @SuppressWarnings("unchecked")
    public Participante obtenerParticipante(Integer idParticipante) {
        Participante participante = null;
        participante = (Participante) genericDao.findByPropertyUnique(Participante.class, "id", idParticipante);
        if (participante == null) {
            throw new ExcepcionNegocio("errors.evento.noExiste");
        }
        return participante;
    }

    public TableroGanancia obtenerTableroPorEquipoyEvento(Date fechaEvento, String nombreEquipo) {
        TableroGanancia tablero = null;
        String query = new String();
        Object[] parametros = {nombreEquipo, fechaEvento};
        query = "Select p from Evento e inner join e.tableroGananciaCollection " +
                "as p where p.participante.nombre = ? and p.evento.fechaEvento = ?";
        tablero = (TableroGanancia) genericDao.ejecutarQueryUnique(query, parametros);
        if (tablero == null) {
            throw new ExcepcionNegocio("evento.noregistrado");
        }
        return tablero;
    }

    public Participante obtenerParticipantePorNombre(String nombre) {
        Participante participante = null;
        participante = (Participante) genericDao.findByPropertyUnique(Participante.class, "nombre", nombre);
        if (participante == null) {
            throw new ExcepcionNegocio("errors.participante.noExiste");
        }
        return participante;
    }

    @SuppressWarnings("unchecked")
    private List<Apuesta> obtenerApuestasPorEvento(Integer idEvento) {
        List<Apuesta> apuestas = new ArrayList<Apuesta>();
        Object[] o = {idEvento};
        String query = "select a from Apuesta a where a.tableroGanancia.tableroGananciaPK.idEvento = ?";
        apuestas.addAll(genericDao.ejecutarQueryList(query, o));

        return apuestas;
    }

    private void updateEventoFinalizado(Integer idEvento, String resultado) {
        Evento evento = this.obtenerEvento(idEvento);
        evento.setFinalizado(Boolean.TRUE);
        evento.setResultado(resultado);
        genericDao.limpiar();
        genericDao.merge(evento);
    }

    private void updateTableroGanancia(Integer idEvento, Integer idParticipante, Boolean gano, Boolean empato) {
        List<TableroGanancia> tableroGanancia = this.obtenerTableroGanancia(idEvento);
        for (TableroGanancia tg : tableroGanancia) {
            TableroGanancia tablero = tg;
            if (tg.getParticipante().getId().equals(idParticipante)) {
                tablero.setGano(gano);
                tablero.setEmpato(empato);
            } else {
                tablero.setEmpato(empato);
                if (empato) {
                    tablero.setGano(Boolean.FALSE);
                } else {
                    tablero.setGano(!gano);
                }
            }
            genericDao.merge(tablero);
        }
    }

    private void updateApuestaGanadora(Integer idEvento, Integer idParticipante, Boolean gano, Boolean empato) {
        List<Apuesta> apuestas = this.obtenerApuestasPorEvento(idEvento);
        for (Apuesta a : apuestas) {
            Apuesta apuesta = a;
            if (a.getTableroGanancia().getParticipante().getId().equals(idParticipante) && a.getGano().equals(gano) && a.getEmpato().equals(empato)) {
                apuesta.setGanador(Boolean.TRUE);
            } else if (a.getEmpato() && empato) {
                apuesta.setGanador(Boolean.TRUE);
            } else {
                apuesta.setGanador(Boolean.FALSE);
            }
            genericDao.merge(apuesta);
        }

    }

    private Boolean finalizarAntes(Integer idEvento) {
        Evento evento = this.obtenerEvento(idEvento);
        Boolean resultado = evento.getIdPolitica().getFinalizarAntes();
        return resultado;
    }

    public void finalizarEvento(Integer idEvento, String resultado, Integer idParticipante, Boolean gano, Boolean empato) {
        if (finalizarAntes(idEvento)) {
            Evento evento = this.obtenerEvento(idEvento);
            if (!(!evento.getIdCategoria().getEmpate() && empato)) {
                this.updateEventoFinalizado(idEvento, resultado);
                this.updateTableroGanancia(idEvento, idParticipante, gano, empato);
                this.updateApuestaGanadora(idEvento, idParticipante, gano, empato);
            } else {
                throw new ExcepcionNegocio("errors.evento.noPermitidoEmpate");
            }
        } else {
            throw new ExcepcionNegocio("errors.evento.finalizarAntes");
        }
    }

    public RespuestaProporcionWS obtenerProporcionEventoExt(String fechaEvento, String equipoUno, String EquipoDos) {
        RespuestaProporcionWS respuesta = null;
        try {
            _211._22._168._192._1234.Ubet service = new _211._22._168._192._1234.Ubet();
            _211._22._168._192._1234.UbetSoap port = service.getUbetSoap12();

            java.lang.String fecha = fechaEvento;
            java.lang.String equipo1 = equipoUno;
            java.lang.String equipo2 = EquipoDos;
            _211._22._168._192._1234.Respuesta result = port.consultarProporcionEvento(fecha, equipo1, equipo2);

            respuesta = new RespuestaProporcionWS();
            respuesta.setEquipoUno(result.getParticipante1());
            respuesta.setEquipoDos(result.getParticipante2());
            respuesta.setProporcionEquipoUno(result.getProporcion1());
            respuesta.setProporcionEquipoDos(result.getProporcion2());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return respuesta;
        }
    }

    public void agregarEvento(Evento evento, TableroGanancia tg1, TableroGanancia tg2) {
        if (evento == null) {
            throw new ExcepcionNegocio("evento.invalido");
        }
        Politica politica = sincronizarPolitica(evento.getIdPolitica());
        evento.setIdPolitica(politica);
        Integer id = genericDao.getNextId(evento);
        evento.setId(id);
        evento.setEstatus(Boolean.TRUE);
        genericDao.insertar(evento);
        Evento eventoInsertado = obtenerEvento(id);
        if (eventoInsertado != null) {
            TableroGananciaPK pk1 = new TableroGananciaPK(id, tg1.getParticipante().getId());
            TableroGananciaPK pk2 = new TableroGananciaPK(id, tg2.getParticipante().getId());
            tg1.setTableroGananciaPK(pk1);
            tg2.setTableroGananciaPK(pk2);
            servicioTableroGanancia.agregarTableroGanancia(tg1);
            servicioTableroGanancia.agregarTableroGanancia(tg2);
        }
    }

    public void editarEvento(Evento evento, TableroGanancia tg1, TableroGanancia tg2) {
        if (evento == null) {
            throw new ExcepcionNegocio("evento.invalido");
        }
        Politica politica = sincronizarPolitica(evento.getIdPolitica());
        evento.setIdPolitica(politica);
        evento.setEstatus(Boolean.TRUE);
        genericDao.merge(evento);
        TableroGananciaPK pk1 = new TableroGananciaPK(evento.getId(), tg1.getParticipante().getId());
        TableroGananciaPK pk2 = new TableroGananciaPK(evento.getId(), tg2.getParticipante().getId());
        tg1.setTableroGananciaPK(pk1);
        tg2.setTableroGananciaPK(pk2);
        servicioTableroGanancia.actualizarTableroGanancia(tg1);
        servicioTableroGanancia.actualizarTableroGanancia(tg2);
    }

    public void activarEvento(Integer idEvento) {
        Evento evento = null;
        evento = obtenerEvento(idEvento);
        evento.setEstatus(Boolean.TRUE);
        genericDao.merge(evento);
    }

    public void desactivarEvento(Integer idEvento) {
        Evento evento = null;
        evento = obtenerEvento(idEvento);
        evento.setEstatus(Boolean.FALSE);
        genericDao.merge(evento);
    }

    @SuppressWarnings("unchecked")
    public List<Evento> todosLosEventos() {
        List<Evento> listaEventos = null;
        listaEventos = genericDao.listar(Evento.class);
        return listaEventos;
    }

    private Politica sincronizarPolitica(Politica politica) {
        Politica politicaAct = null;
        Object o[] = {politica.getMontoMaximo(), politica.getFinalizarAntes()};
        String query = "Select p From Politica p where p.montoMaximo = ? and p.finalizarAntes = ?";
        politicaAct = (Politica) genericDao.ejecutarQueryUnique(query, o);
        if (politicaAct == null) {
            Integer id = genericDao.getNextId(politica);
            politica.setId(id);
            genericDao.insertar(politica);
            return politica;
        } else {
            return politicaAct;
        }
    }
}
