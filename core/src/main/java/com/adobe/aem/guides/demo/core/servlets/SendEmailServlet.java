package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;

@Component(
    service = { Servlet.class },
    property = {
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
        "sling.servlet.paths=/bin/sendEmail/hemus"
    }
)
public class SendEmailServlet extends SlingAllMethodsServlet {

    @Reference
    private MessageGatewayService messageGatewayService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String to = "hnellori@gmail.com";
        String subject = "Test Email";
        String body = "This is a test email from AEM.";

        try {
            sendEmail(to, subject, body);
            response.getWriter().write("Email sent successfully.");
        } catch (EmailException e) {
            response.getWriter().write("Failed to send email: " + e.getMessage());
        }
    }

    private void sendEmail(String to, String subject, String body) throws EmailException {
        // Create the email message
        HtmlEmail email = new HtmlEmail();
        email.setCharset("UTF-8");
        email.setSubject(subject);
        email.setHtmlMsg(body);
        email.addTo(to);
        email.setFrom("hemanth.nellori@gmail.com");

        // Retrieve the MessageGateway service and send the email
        MessageGateway<HtmlEmail> messageGateway = messageGatewayService.getGateway(HtmlEmail.class);

        if (messageGateway != null) {
            messageGateway.send(email);
        } else {
            throw new EmailException("Message Gateway could not be retrieved.");
        }
    }
}