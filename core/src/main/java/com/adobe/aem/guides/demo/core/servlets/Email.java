package com.adobe.aem.guides.demo.core.servlets;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    public static void main(String[] args) {
        // SMTP server details
        String smtpHost = "smtp.gmail.com"; // Replace with your SMTP server
        int smtpPort = 587; // Replace with the port of your SMTP server
        final String username = "hemanthsainellori@gmail.com"; // Replace with your email address
        final String password = "yofmzgflukuqojtr"; // Replace with your email password

        // Sender's email address
        String fromEmail = "hemanthsainellori@gmail.com"; // Replace with your email address

        // Recipient's email address
        String toEmail = "hnellori@gmail.com"; // Replace with recipient's email address

        // Set properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        // Create session
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            // Set From: header field of the header
            message.setFrom(new InternetAddress(fromEmail));

            // Set To: header field of the header
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            // Set Subject: header field
            message.setSubject("Testing JavaMail");

            // Now set the actual message
            message.setText("Hello, this is a test message from JavaMail.");

            // Send message
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
