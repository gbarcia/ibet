package com.pd.generic.util.mail.interfaces;

import java.io.File;

/**
 * Interfaz para ofrecer el servicio de enviar correos electronicos
 * @author Gerardo Barcia
 * @version 1.0
 */
public interface IMailService {

    /**
     * firma para enviar un correo electronico sin archivos adjuntos
     * @param to direccion de correo destino
     * @param subject asunto del correo
     * @param text cuerpo del correo
     */
    public void send(String to, String subject, String text);

    /**
     * firma para enviar correo electronico con archivos adjuntos
     * @param to direccion de correo destino
     * @param subject asunto del correo
     * @param text cuerpo del correo
     * @param attachments archivos adjuntos
     */
    public void send(String to, String subject, String text, File... attachments);
}
