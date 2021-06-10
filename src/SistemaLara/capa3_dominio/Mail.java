/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa3_dominio;

import java.util.Properties;
import javax.mail.Session;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author mineria
 */
public class Mail {

    public static boolean enviarCorreo(CorreoFactura correoFactura, String correo) {
        boolean enviado = false;
        try {
            String correoFrom = IniciarSesion.getUsuario().getEmail();
            String password = IniciarSesion.getUsuario().getPassword();
            BodyPart adjuntopdf = null, adjuntoxml = null;
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", correoFrom);
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(true);

            BodyPart texto = new MimeBodyPart();
            texto.setText(correoFactura.getMensaje());

            if (!correoFactura.getElectronico().getRutapdf().isEmpty()) {
                adjuntopdf = new MimeBodyPart();
                adjuntopdf.setDataHandler(new DataHandler(new FileDataSource(correoFactura.getElectronico().getRutapdf())));
                adjuntopdf.setFileName(correoFactura.getElectronico().getNumeroFactura() + ".pdf");
            }
            if (!correoFactura.getElectronico().getRutaxml().isEmpty()) {
                adjuntoxml = new MimeBodyPart();
                adjuntoxml.setDataHandler(new DataHandler(new FileDataSource(correoFactura.getElectronico().getRutaxml())));
                adjuntoxml.setFileName(correoFactura.getElectronico().getNumeroFactura() + ".xml");
            }

            MimeMultipart multiParte = new MimeMultipart();

            multiParte.addBodyPart(texto);
            if (adjuntopdf != null) {
                multiParte.addBodyPart(adjuntopdf);
            }
            if (adjuntoxml != null) {
                multiParte.addBodyPart(adjuntoxml);
            }

            MimeMessage message = new MimeMessage(session);

// Se rellena el From
            message.setFrom(new InternetAddress(correoFrom));

// Se rellenan los destinatarios
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));

// Se rellena el subject
            message.setSubject(correoFactura.getAsunoto());

// Se mete el texto y la foto adjunta.
            message.setContent(multiParte);

            Transport t = session.getTransport("smtp");
            t.connect(correoFrom, password);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            enviado = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return enviado;

    }

}
