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
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.to.forms.RegistroEventoTO;
import ve.edu.ucab.ibet.dominio.to.ws.RespuestaProporcionWS;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.generic.util.mail.interfaces.IMailService;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioEvento;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioTableroGanancia;
import winterwell.jtwitter.Twitter;

/**
 * Clase para ofrecer servicios de Evento
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ServicioEventoImpl implements IServicioEvento {

    private IGenericDao genericDao;
    private IHelperProperties helperProp;
    private IServicioTableroGanancia servicioTableroGanancia;
    private IMailService servicioMail;

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

    public IMailService getServicioMail() {
        return servicioMail;
    }

    public void setServicioMail(IMailService servicioMail) {
        this.servicioMail = servicioMail;
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

    @SuppressWarnings("unchecked")
    public List<Evento> obtenerProximosEventos(Integer inicio, Integer fin) {
        List<Evento> eventos = new ArrayList<Evento>();
        List<Evento> resultado = new ArrayList<Evento>();
        String query = "select a from Categoria c inner join c.eventoCollection as a " +
                "where c.habilitada = 1" +
                "and c.idCategoria.habilitada = 1" +
                "and a.fechaEvento >= current_date " +
                "and a.estatus = 1 " +
                "and a.finalizado = 0 " +
                "order by a.fechaEvento, a.hora";
        Object[] parametros = {};
        eventos.addAll(genericDao.ejecutarQueryList(query, parametros, inicio, fin));
        for (Evento e : eventos) {
            ArrayList<TableroGanancia> t = new ArrayList<TableroGanancia>(e.getTableroGananciaCollection());
            String participanteUno = t.get(0).getParticipante().getNombre();
            String participanteDos = t.get(1).getParticipante().getNombre();
            e.setProporcion(this.obtenerProporcionEventoExt(UtilMethods.convertirFechaFormatoUbet(e.getFecha()), participanteUno, participanteDos));
            resultado.add(e);
        }
        return resultado;
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

    private void enviarCorreoFinEvento(Users u, Apuesta a) {
        List<String> datosCorreo = new ArrayList<String>();
        String titulo = (u.getSexo().equalsIgnoreCase("M")) ? "Sr" : "Sra";
        String miResultado = a.isGanador() ? "GANE" : "PERDI";
        Double balance = a.getMonto();
        balance *= (a.isGanador()) ? a.getTableroGanancia().getPropocionGano() : (-1);
        datosCorreo.add(a.getTableroGanancia().getEvento().getNombre());
        datosCorreo.add(titulo);
        datosCorreo.add(u.getNombre() + " " + u.getApellido());
        datosCorreo.add(a.getTableroGanancia().getEvento().getResultado());
        datosCorreo.add(a.getTableroGanancia().getParticipante().getNombre());
        datosCorreo.add(a.getTableroGanancia().getEvento().getResultado());
        datosCorreo.add(a.getMonto().toString());
        datosCorreo.add(miResultado);
        datosCorreo.add(balance.toString());
        String asunto = helperProp.getString("correos.notificacion.finevento.asunto", datosCorreo);
        String cuerpo = ("<p>" + helperProp.getString("correos.notificacion.finevento.mensaje.linea1", datosCorreo) + "<p>");
        cuerpo += ("<p>" + helperProp.getString("correos.notificacion.finevento.mensaje.linea2", datosCorreo) + "<p>");
        cuerpo += ("<p>" + helperProp.getString("correos.notificacion.finevento.mensaje.linea3") + "<p>");
        cuerpo += ("<p>" + helperProp.getString("correos.notificacion.finevento.mensaje.linea4", datosCorreo) + "<p>");
        cuerpo += ("<p>" + helperProp.getString("correos.notificacion.finevento.mensaje.linea5", datosCorreo) + "<p>");
        cuerpo += ("<p>" + helperProp.getString("correos.notificacion.finevento.mensaje.linea6", datosCorreo) + "<p>");
        cuerpo += ("<p>" + helperProp.getString("correos.notificacion.finevento.mensaje.linea7", datosCorreo) + "<p>");
        cuerpo += ("<p>" + helperProp.getString("correos.notificacion.finevento.mensaje.linea8", datosCorreo) + "<p>");
        cuerpo += ("<p>" + helperProp.getString("correos.notificacion.finevento.mensaje.linea9") + "<p>");
        servicioMail.send(u.getCorreo(), asunto, cuerpo);
    }

    private void updateEventoFinalizado(Integer idEvento, String resultado) {
        Evento evento = this.obtenerEvento(idEvento);
        evento.setFinalizado(Boolean.TRUE);
        evento.setEstatus(Boolean.FALSE);
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
            Users usuario = apuesta.getUsers();
            if (a.getTableroGanancia().getParticipante().getId().equals(idParticipante) && a.getGano().equals(gano) && a.getEmpato().equals(empato)) {
                apuesta.setGanador(Boolean.TRUE);
            } else if (a.getEmpato() && empato) {
                apuesta.setGanador(Boolean.TRUE);
            } else {
                apuesta.setGanador(Boolean.FALSE);
            }
            genericDao.merge(apuesta);
            enviarCorreoFinEvento(usuario, a);
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
            Integer pId = idParticipante;
            ArrayList<TableroGanancia> tableros = new ArrayList<TableroGanancia>(evento.getTableroGananciaCollection());
            if (idParticipante == null) {
                pId = tableros.get(0).getParticipante().getId();
            }
            if (!(!evento.getIdCategoria().getEmpate() && empato)) {
                this.updateEventoFinalizado(idEvento, resultado);
                this.updateTableroGanancia(idEvento, pId, gano, empato);
                this.updateApuestaGanadora(idEvento, pId, gano, empato);
                Twitter twitter = new Twitter(helperProp.getString("tw.username"), helperProp.getString("tw.pass"));
                Double proporcion = 0.0;
                for (TableroGanancia tg : evento.getTableroGananciaCollection()) {
                    if (tg.getParticipante().getId().equals(pId)) {
                        proporcion = empato ? tg.getProporcionEmpate() : tg.getPropocionGano();
                    }
                }
                twitter.updateStatus("Evento: " + evento.getNombre() + ". " + resultado + ". Paga: " + proporcion + " \n Felicitaciones a los ganadores!");
            } else {
                throw new ExcepcionNegocio("errors.evento.noPermitidoEmpate");
            }
        } else {
            throw new ExcepcionNegocio("errors.evento.finalizarAntes");
        }
    }

    /**
     * Operacion de consulta para obtener la proporcion de pagos para una puesta
     * de otras casas, por medio de una operacion web. El codigo se encuentra operativo
     * en formato en tiempo real para trabajar con el web service o un pequeno simulador
     * que retorna objetos para realizar las pruebas pertinentes sin necesidad de
     * tener el servicio web operativo, para beneficios del equipo de desarrollo
     * @param fechaEvento fecha del evento
     * @param equipoUno nombre del equipo uno
     * @param EquipoDos nombre del equipo dos
     * @return objeto RespuestaProporcionWS con la informacion
     */
    public RespuestaProporcionWS obtenerProporcionEventoExt(String fechaEvento, String equipoUno, String EquipoDos) {
        RespuestaProporcionWS respuesta = null;
        // BLOQUE DE CODIGO WS
//        try {
//            _211._22._168._192._1234.Ubet service = new _211._22._168._192._1234.Ubet();
//            _211._22._168._192._1234.UbetSoap port = service.getUbetSoap12();
//
//            java.lang.String fecha = fechaEvento;
//            java.lang.String equipo1 = equipoUno;
//            java.lang.String equipo2 = EquipoDos;
//            _211._22._168._192._1234.Respuesta result = port.consultarProporcionEvento(fecha, equipo1, equipo2);
//
//            if (result != null) {
//            respuesta = new RespuestaProporcionWS();
//            respuesta.setEquipoUno(result.getParticipante1());
//            respuesta.setEquipoDos(result.getParticipante2());
//            respuesta.setProporcionEquipoUno(result.getProporcion1());
//            respuesta.setProporcionEquipoDos(result.getProporcion2());
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            return respuesta;
//        }
// BLOQUE DE CODIGO PRUEBA
        int numero = (int) (Math.random() * 2 + 1);
        if (numero == 1) {
            respuesta = null;
        } else {
            respuesta = new RespuestaProporcionWS();
            respuesta.setProporcionEquipoUno(3.5);
            respuesta.setProporcionEquipoDos(2.0);
        }
        return respuesta;
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
        String query = "Select e from Evento e where e.finalizado = 0 order by e.fechaEvento DESC, e.idCategoria, e.estatus";
        listaEventos = genericDao.ejecutarQueryList(query);
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

    public Evento transferObjectToEvento(RegistroEventoTO registro) {
        Evento evento = new Evento();
        evento.setFecha(registro.getFechaEvento());
        evento.setFechaMaxima(registro.getFechaMax());
        evento.setHora(UtilMethods.stringToHora(registro.getHoraEvento() + ":00"));
        evento.setHoraMaxima(UtilMethods.stringToHora(registro.getHoraMax() + ":00"));
        evento.setIdCategoria(registro.getCategoria());
        evento.setIdPolitica(registro.getPolitica());
        evento.setImagenEvento(registro.getImagenEvento().getOriginalFilename());
        evento.setNombre(registro.getNombreEvento());
        evento.setResultado("");
        return evento;
    }
}
