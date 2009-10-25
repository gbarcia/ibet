package ve.edu.ucab.ibet.generic.util.mail.impl;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Assert;
import ve.edu.ucab.ibet.generic.util.mail.interfaces.IMailService;

/**
 * clase para ofrecer la implementacion de un servicio con proposito de enviar
 * correos electronicos, con la modalidad de poder agregarle archivos adjuntos
 * @author gerardo barcia
 * @version 1.0
 */
public class MailServiceImpl implements IMailService {

    /** wrapper de Spring sobre javax.mail */
    private JavaMailSenderImpl mailSender;
    /** correo electrónico del remitente */
    private String from;
    /** flag para indicar si está activo el servicio */
    public boolean active;
    private static final File[] NO_ATTACHMENTS = null;

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /** envío de email
     * @param to correo electrónico del destinatario
     * @param subject asunto del mensaje
     * @param text cuerpo del mensaje
     */
    public void send(String to, String subject, String text) {
        send(to, subject, text, NO_ATTACHMENTS);
    }
    /**
     * envio de mail con attachments
     * @param to correo electrónico del destinatario
     * @param subject asunto del mensaje
     * @param text mensaje
     * @param attachments ficheros que se anexarán al mensaje
     */
    public void send(String to, String subject, String text, File... attachments) {
        final MimeMessage message = mailSender.createMimeMessage();

        Assert.hasLength(to, "email 'to' needed");
        Assert.hasLength(subject, "email 'subject' needed");
        Assert.hasLength(text, "email 'text' needed");

        if (!active) {
            return;
        }
        try {
            final MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(getFrom());
            helper.setText(text,true);
            if (attachments != null) {
                for (int i = 0; i < attachments.length; i++) {
                    FileSystemResource file = new FileSystemResource(attachments[i]);
                    helper.addAttachment(attachments[i].getName(), file);
                }
            }
        } catch (MessagingException ex) {
            Logger.getLogger(MailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.mailSender.send(message);
    }
}
