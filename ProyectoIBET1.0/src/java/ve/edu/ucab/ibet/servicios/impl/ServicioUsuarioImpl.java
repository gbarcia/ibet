package ve.edu.ucab.ibet.servicios.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.security.annotation.Secured;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.UsuarioMedioPago;
import ve.edu.ucab.ibet.dominio.enums.Role;
import ve.edu.ucab.ibet.dominio.to.forms.PerfilUsuarioTO;
import ve.edu.ucab.ibet.dominio.to.forms.RegistroUsuarioTO;
import ve.edu.ucab.ibet.dominio.to.reportes.DetallesGananciasUsuarioTO;
import ve.edu.ucab.ibet.dominio.to.reportes.GananciasPorUsuarioTO;
import ve.edu.ucab.ibet.generic.dao.interfaces.IGenericDao;
import ve.edu.ucab.ibet.generic.excepciones.negocio.ExcepcionNegocio;
import ve.edu.ucab.ibet.generic.util.PassGenerator;
import ve.edu.ucab.ibet.generic.util.UtilMethods;
import ve.edu.ucab.ibet.generic.util.helpers.interfaces.IHelperProperties;
import ve.edu.ucab.ibet.generic.util.mail.interfaces.IMailService;
import ve.edu.ucab.ibet.servicios.interfaces.IServicioUsuario;

/**
 * Clase que ofrece servicios para el manejo de usuarios
 * @author Gerardo Barcia
 * @version 1.0
 */
public class ServicioUsuarioImpl implements IServicioUsuario {

    private IMailService servicioMail;
    private IGenericDao genericDao;
    private IHelperProperties helperProp;
    private Md5PasswordEncoder md5 = new Md5PasswordEncoder();

    public IHelperProperties getHelperProp() {
        return helperProp;
    }

    public void setHelperProp(IHelperProperties helperProp) {
        this.helperProp = helperProp;
    }

    public IGenericDao getGenericDao() {
        return genericDao;
    }

    public void setGenericDao(IGenericDao genericDao) {
        this.genericDao = genericDao;
    }

    public IMailService getServicioMail() {
        return servicioMail;
    }

    public void setServicioMail(IMailService servicioMail) {
        this.servicioMail = servicioMail;
    }

    private void enviarCorreo(Users user) {
        InetAddress direccion = null;
        try {
            direccion = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
            throw new ExcepcionNegocio(helperProp.getString("error.server.mailnosend"));
        }
        byte[] direccionIp = direccion.getAddress();
        List<String> datosCorreo = new ArrayList<String>();
        String titulo = (user.getSexo().equalsIgnoreCase("M")) ? "Sr" : "Sra";
        String url = "http://" + direccionIp[0] +
                "." + direccionIp[1] +
                "." + direccionIp[2] +
                "." + direccionIp[3] +
                ":8084/ProyectoIBET/publico/confirmarRegistro.htm?user=" + UtilMethods.encrypt(user.getUsername());
        datosCorreo.add(titulo);
        datosCorreo.add(user.getNombre() + " " + user.getApellido());
        datosCorreo.add(user.getUsername());
        datosCorreo.add(user.getPassword());
        datosCorreo.add(url);
        String asunto = helperProp.getString("correos.registro.plantillas.asunto.registro");
        String cuerpo = ("<p>" + helperProp.getString("correos.registro.plantillas.cuerpo.encabezado", datosCorreo) + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.registro.plantillas.cuerpo.mensaje.linea1") + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.registro.plantillas.cuerpo.mensaje.linea2") + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.registro.plantillas.cuerpo.mensaje.linea3") + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.registro.plantillas.cuerpo.mensaje.linea4", datosCorreo) + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.registro.plantillas.cuerpo.mensaje.linea5", datosCorreo) + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.registro.plantillas.cuerpo.mensaje.linea6") + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.registro.plantillas.cuerpo.mensaje.linea7") + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.registro.plantillas.cuerpo.mensaje.linea8") + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.registro.plantillas.cuerpo.mensaje.linea9", datosCorreo) + "</p>");

        servicioMail.send(user.getCorreo(), asunto, cuerpo);
    }

    public boolean existeUsuarioM(Users u) {
        boolean resultado = Boolean.TRUE;
        Users usuarioEnBd = (Users) genericDao.findByPropertyUnique(Users.class, "username", u.getUsername());
        if (usuarioEnBd == null) {
            usuarioEnBd = (Users) genericDao.findByPropertyUnique(Users.class, "correo", u.getCorreo());
            if (usuarioEnBd == null) {
                resultado = Boolean.FALSE;
            }
        }
        return resultado;
    }

    private boolean esUsuarioMayorEdad(Users u) {
        boolean esMayor = Boolean.FALSE;
        Integer anioActual = UtilMethods.extraerAnio(new Date());
        Integer anioUsuario = UtilMethods.extraerAnio(u.getFechaNacimiento());
        Integer resultado = anioActual - anioUsuario;
        if (resultado >= 18) {
            esMayor = Boolean.TRUE;
        }
        return esMayor;
    }

    private void asignarRolUsuario(Role rol, Users usuario) {
        String username = usuario.getUsername();
        String rolUsuario = rol.roleName();
        StringBuffer query = new StringBuffer("");
        query.append("INSERT INTO authorities VALUES (");
        query.append("'" + rolUsuario + "'" + ",");
        query.append("'" + username + "'" + ")");
        genericDao.ejecturarSQLQueryManipulacion(query.toString());
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void registroNuevoUsuarioM(Users user) {
        if (!existeUsuarioM(user)) {
            if (esUsuarioMayorEdad(user)) {
                enviarCorreo(user);
                user.setPassword(md5.encodePassword(user.getPassword(), null));
                user.setEnabled(false);
                user.setConfirmado(false);
                genericDao.insertar(user);
                asignarRolUsuario(Role.USER_ROLE, user);
            } else {
                throw new ExcepcionNegocio(helperProp.getString("error.negocio.usuariomenor"));
            }
        } else {
            throw new ExcepcionNegocio(helperProp.getString("error.negocio.usuariorepetido"));
        }
    }

    public String obtenerAtributoError(String mensaje) {
        String resultado = null;
        if (mensaje.equals(helperProp.getString("error.negocio.usuariomenor"))) {
            resultado = "fechaNacimiento";
        } else if (mensaje.equals(helperProp.getString("error.negocio.usuariorepetido"))) {
            resultado = "nombreUsuario";
        } else if (mensaje.equals("error.usuario.invalido.dif")) {
            resultado = "username";
        }
        return resultado;
    }

    public Users obtenerDatosUsuarioM(String username) {
        Users user = null;
        user = (Users) genericDao.findByPropertyUnique(Users.class, "username", username);
        if (user == null) {
            throw new ExcepcionNegocio(helperProp.getString("error.negocio.usuarionoexiste"));
        }
        return user;

    }

    @Secured({"ROLE_USER"})
    public void actualizarDatosUsuarioM(Users user) {
        if (!user.getPassword().isEmpty()) {
            enviarCorreoCambioClave(user);
            user.setPassword(md5.encodePassword(user.getPassword(), null));
        } else {
            Users usuarioactual = obtenerDatosUsuarioM(user.getUsername());
            user.setPassword(usuarioactual.getPassword());
        }
        user.setEnabled(Boolean.TRUE);
        user.setConfirmado(Boolean.TRUE);
        genericDao.limpiar();
        genericDao.modificar(user);
    }

    private void enviarCorreoCambioClave(Users user) {
        List<String> datosCorreo = new ArrayList<String>();
        String titulo = (user.getSexo().equalsIgnoreCase("M")) ? "Sr" : "Sra";
        datosCorreo.add(titulo);
        datosCorreo.add(user.getNombre() + " " + user.getApellido());
        datosCorreo.add(user.getUsername());
        datosCorreo.add(user.getPassword());
        String asunto = helperProp.getString("correos.cambioclave.plantillas.asunto");
        String cuerpo = ("<p>" + helperProp.getString("correos.cambioclave.plantillas.mensaje.linea1", datosCorreo) + "<p>");
        cuerpo += ("<p>" + helperProp.getString("correos.cambioclave.plantillas.mensaje.linea2") + "<p>");
        cuerpo += ("<p>" + helperProp.getString("correos.cambioclave.plantillas.mensaje.linea3", datosCorreo) + "<p>");
        cuerpo += ("<p>" + helperProp.getString("correos.cambioclave.plantillas.mensaje.linea4", datosCorreo) + "<p>");
        cuerpo += ("<p>" + helperProp.getString("correos.cambioclave.plantillas.mensaje.linea5") + "<p>");
        cuerpo += ("<p>" + helperProp.getString("correos.cambioclave.plantillas.mensaje.linea6") + "<p>");
        servicioMail.send(user.getCorreo(), asunto, cuerpo);
    }

    public Users transferObjectToModel(RegistroUsuarioTO to) {
        Users usuario = null;
        if (to != null) {
            usuario = new Users();
            usuario.setUsername(to.getNombreUsuario());
            usuario.setPassword(to.getClave());
            usuario.setNombre(to.getNombre());
            usuario.setApellido(to.getApellido());
            usuario.setFechaNacimiento(to.getFechaNacimiento());
            usuario.setCorreo(to.getCorreo());
            usuario.setSexo(to.getSexo());
            usuario.setTelefono(to.getTelefono());
            usuario.setCalle(to.getCalle());
            usuario.setCiudad(to.getCiudad());
            usuario.setCodigoPostal(to.getCodigoPostal());
            usuario.setEstado(to.getEstado());
            usuario.setPais(to.getPais());
        }
        return usuario;
    }

    public void confirmarRegistroUsuario(String username) {
        Users usuario = this.obtenerDatosUsuarioM(username);
        usuario.setEnabled(Boolean.TRUE);
        usuario.setConfirmado(Boolean.TRUE);
        genericDao.merge(usuario);
    }

    public void habilitarUsuario(String username) {
        Users usuario = this.obtenerDatosUsuarioM(username);
        usuario.setEnabled(Boolean.TRUE);
    }

    public void deshabilitarUsuario(String username) {
        Users usuario = this.obtenerDatosUsuarioM(username);
        usuario.setEnabled(Boolean.FALSE);
    }

    public void recuperarClave(String username) {
        String nuevaClave = PassGenerator.getNext();
        Users user = obtenerDatosUsuarioM(username);
        user.setPassword(md5.encodePassword(nuevaClave, null));
        genericDao.limpiar();
        genericDao.merge(user);
        enviarCorreoRecuperacionClave(user, nuevaClave);
    }

    public void enviarConfirmacionRecupClave(String correo, String username) {
        Users usuario = obtenerUsuarioPorCorreo(correo);
        if (usuario.getUsername().equals(username)) {
            List<String> datosCorreo = new ArrayList<String>();
            String titulo = (usuario.getSexo().equalsIgnoreCase("M")) ? "Sr" : "Sra";
            String url = "http://localhost" +
                    ":8084/ProyectoIBET/publico/recuperacionClave.htm?user=" + UtilMethods.encrypt(usuario.getUsername());
            datosCorreo.add(titulo);
            datosCorreo.add(usuario.getNombre() + " " + usuario.getApellido());
            datosCorreo.add(url);
            String asunto = helperProp.getString("correos.recuperacion.clave.plantillas.asunto");
            String cuerpo = ("<p>" + helperProp.getString("correos.recuperacion.clave.plantillas.mensaje.linea1", datosCorreo) + "</p>");
            cuerpo += ("<p>" + helperProp.getString("correos.recuperacion.clave.plantillas.mensaje.linea2") + "</p>");
            cuerpo += ("<p>" + helperProp.getString("correos.recuperacion.clave.plantillas.mensaje.linea3") + "</p>");
            cuerpo += ("<p>" + helperProp.getString("correos.recuperacion.clave.plantillas.mensaje.linea4", datosCorreo) + "</p>");
            cuerpo += ("<p>" + helperProp.getString("correos.recuperacion.clave.plantillas.mensaje.linea5") + "</p>");
            cuerpo += ("<p>" + helperProp.getString("correos.recuperacion.clave.plantillas.mensaje.linea6") + "</p>");

            servicioMail.send(usuario.getCorreo(), asunto, cuerpo);
        } else {
            throw new ExcepcionNegocio("error.usuario.invalido.dif");
        }
    }

    public Users obtenerUsuarioPorCorreo(String correo) {
        Users user = null;
        user = (Users) genericDao.findByPropertyUnique(Users.class, "correo", correo);
        if (user == null) {
            throw new ExcepcionNegocio("error.usuario.invalido.dif");
        }
        return user;
    }

    private void enviarCorreoRecuperacionClave(Users usuario, String nuevaClave) {
        List<String> datosCorreo = new ArrayList<String>();
        String titulo = (usuario.getSexo().equalsIgnoreCase("M")) ? "Sr" : "Sra";
        datosCorreo.add(titulo);
        datosCorreo.add(usuario.getNombre() + " " + usuario.getApellido());
        datosCorreo.add(usuario.getUsername());
        datosCorreo.add(nuevaClave);
        String asunto = helperProp.getString("correos.recuperacion.clave.exito.plantillas.asunto");
        String cuerpo = ("<p>" + helperProp.getString("correos.recuperacion.clave.exito.plantillas.mensaje.linea1", datosCorreo) + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.recuperacion.clave.exito.plantillas.mensaje.linea2") + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.recuperacion.clave.exito.plantillas.mensaje.linea3", datosCorreo) + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.recuperacion.clave.exito.plantillas.mensaje.linea4", datosCorreo) + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.recuperacion.clave.exito.plantillas.mensaje.linea5") + "</p>");
        cuerpo += ("<p>" + helperProp.getString("correos.recuperacion.clave.exito.plantillas.mensaje.linea6") + "</p>");

        servicioMail.send(usuario.getCorreo(), asunto, cuerpo);
    }

    public RegistroUsuarioTO modelToTransferObject(Users u) {
        RegistroUsuarioTO registroUsuario = new RegistroUsuarioTO();
        registroUsuario.setApellido(u.getApellido());
        registroUsuario.setCalle(u.getCalle());
        registroUsuario.setCiudad(u.getCiudad());
        registroUsuario.setCodigoPostal(u.getCodigoPostal());
        registroUsuario.setCorreo(u.getCorreo());
        registroUsuario.setEstado(u.getEstado());
        registroUsuario.setFechaNacimiento(u.getFechaNacimiento());
        registroUsuario.setNombre(u.getNombre());
        registroUsuario.setNombreUsuario(u.getUsername());
        registroUsuario.setPais(u.getPais());
        registroUsuario.setSexo(u.getSexo());
        registroUsuario.setTelefono(u.getTelefono());
        return registroUsuario;
    }

    public PerfilUsuarioTO modelToTransferObjectPerfil(Users u) {
        PerfilUsuarioTO perfilUsuario = new PerfilUsuarioTO();
        perfilUsuario.setApellido(u.getApellido());
        perfilUsuario.setCalle(u.getCalle());
        perfilUsuario.setCiudad(u.getCiudad());
        perfilUsuario.setCodigoPostal(u.getCodigoPostal());
        perfilUsuario.setCorreo(u.getCorreo());
        perfilUsuario.setEstado(u.getEstado());
        perfilUsuario.setFechaNacimiento(u.getFechaNacimiento());
        perfilUsuario.setNombre(u.getNombre());
        perfilUsuario.setNombreUsuario(u.getUsername());
        perfilUsuario.setPais(u.getPais());
        perfilUsuario.setSexo(u.getSexo());
        perfilUsuario.setTelefono(u.getTelefono());
        return perfilUsuario;
    }

    public Users transferObjectToModelPerfil(PerfilUsuarioTO to) {
        Users usuario = null;
        if (to != null) {
            usuario = new Users();
            usuario.setUsername(to.getNombreUsuario());
            usuario.setPassword(to.getClave());
            usuario.setNombre(to.getNombre());
            usuario.setApellido(to.getApellido());
            usuario.setFechaNacimiento(to.getFechaNacimiento());
            usuario.setCorreo(to.getCorreo());
            usuario.setSexo(to.getSexo());
            usuario.setTelefono(to.getTelefono());
            usuario.setCalle(to.getCalle());
            usuario.setCiudad(to.getCiudad());
            usuario.setCodigoPostal(to.getCodigoPostal());
            usuario.setEstado(to.getEstado());
            usuario.setPais(to.getPais());
        }
        return usuario;
    }

    @SuppressWarnings("unchecked")
    public List<UsuarioMedioPago> obtenerMediosPagoVigenteUsuario(Users usuario) {
        String query = "select distinct c from MedioPago m, Users u inner join u.usuarioMedioPagoCollection " +
                " as c where u.username = ? and c.fechaFin is null " +
                "and c.activo = true and m.activo=true";
        Object[] parametros = {usuario.getUsername()};
        List<UsuarioMedioPago> listaMediosPago = genericDao.ejecutarQueryList(query, parametros);
        return listaMediosPago;
    }

    public Users comprobarValidezUsuario(String username, String pass) {
        Users usuario = null;
        usuario = (Users) genericDao.findByPropertyUnique(Users.class, "username", username);
        if (usuario == null) {
            throw new ExcepcionNegocio("username.no.existe");
        }
        if (!usuario.getPassword().equals(pass)) {
            throw new ExcepcionNegocio("username.clave.incorrecta");
        }
        return usuario;
    }

    @SuppressWarnings("unchecked")
    public DetallesGananciasUsuarioTO obtenerGananciasPorUsuario(String username) {
        List<GananciasPorUsuarioTO> ganancias = new ArrayList<GananciasPorUsuarioTO>();

        Object[] o = new Object[1];
        o[0] = username;

        String query = "select New ve.edu.ucab.ibet.dominio.to.reportes.GananciasPorUsuarioTO (a.monto, e.nombre, e.fechaEvento, tg.gano, tg.proporcionGano, tg.proporcionEmpate) " +
                       "from Categoria c, Evento e, TableroGanancia tg, Apuesta a, Users u, Participante p " +
                       "where c.id = e.idCategoria " +
                       "and e.id = tg.tableroGananciaPK.idEvento " +
                       "and p.id = tg.tableroGananciaPK.idParticipante " +
                       "and tg.tableroGananciaPK.idEvento = a.tableroGanancia.evento.id " +
                       "and tg.tableroGananciaPK.idParticipante = a.tableroGanancia.participante.id " +
                       "and u.username = a.users.username " +
                       "and u.username = ? " +
                       "and a.ganador = true ";

        ganancias.addAll(genericDao.ejecutarQueryList(query, o));

        ganancias = this.gananciasPorUsuario(ganancias);

        DetallesGananciasUsuarioTO detalles = new DetallesGananciasUsuarioTO();
        detalles.setGananciasPorUsuario(ganancias);

        return detalles;
    }

    private List<GananciasPorUsuarioTO> gananciasPorUsuario(List<GananciasPorUsuarioTO> ganancias){
        
        Double montoNuevo = 0.0;
        for (GananciasPorUsuarioTO ganancia : ganancias) {
            if(ganancia.getGano()){
                montoNuevo = ganancia.getMontoGanado()*ganancia.getProporcionGano();
                ganancia.setMontoGanado(montoNuevo);
            } else {
                montoNuevo = ganancia.getMontoGanado()*ganancia.getProporcionEmpate();
                ganancia.setMontoGanado(montoNuevo);
            }
        }
        return ganancias;
    }
}
