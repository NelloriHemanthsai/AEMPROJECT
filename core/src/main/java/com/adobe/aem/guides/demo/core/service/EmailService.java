package com.adobe.aem.guides.demo.core.service;

import org.osgi.service.component.annotations.Component;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Component(immediate = true, service = EmailService.class)
public class EmailService {

    public void sendEmail(String to, String subject, String body) throws MessagingException {
        String from = "hnellori@gmail.com";
        final String username = "hemanth";
        final String password = "hemanth$123456";
        String host = "smtp.example.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }
}
