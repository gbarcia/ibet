package ve.edu.ucab.ibet.servicios.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ve.edu.ucab.ibet.dominio.Users;
import ve.edu.ucab.ibet.dominio.to.forms.RegistroUsuarioTO;
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

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void registroNuevoUsuarioM(Users user) {
        if (!existeUsuarioM(user)) {
            // if (esUsuarioMayorEdad(user)) {
            enviarCorreo(user);
            user.setPassword(md5.encodePassword(user.getPassword(), null));
            user.setEnabled(false);
            user.setConfirmado(false);
            genericDao.insertar(user);
            //} else {
            // throw new ExcepcionNegocio(helperProp.getString("error.negocio.usuariomenor"));
            //  }
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
        }
        else if (mensaje.equals("error.usuario.invalido.dif")){
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

    public void actualizarDatosUsuarioM(Users user) {
        genericDao.merge(user);
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

    public void recuperarClave(String correo) {
        String nuevaClave = PassGenerator.getNext();
        Users user = obtenerUsuarioPorCorreo(correo);
        user.setPassword(md5.encodePassword(nuevaClave, null));
        actualizarDatosUsuarioM(user);
        enviarCorreoRecuperacionClave(user, nuevaClave);
    }

    public void enviarConfirmacionRecupClave(String correo, String username) {
        Users usuario = obtenerUsuarioPorCorreo(correo);
        if (usuario.getUsername().equals(username)) {
            List<String> datosCorreo = new ArrayList<String>();
            String titulo = (usuario.getSexo().equalsIgnoreCase("M")) ? "Sr" : "Sra";
            String url = "http://localhost" +
                    ":8084/ProyectoIBET/publico/confirmarCambioClave.htm?user=" + UtilMethods.encrypt(usuario.getUsername());
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
}
