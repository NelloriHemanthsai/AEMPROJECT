package com.adobe.aem.guides.demo.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.framework.Constants;
import org.apache.sling.api.servlets.HttpConstants;

import java.io.IOException;

@Component(service = javax.servlet.Servlet.class,
    property = {
        Constants.SERVICE_DESCRIPTION + "=Login Servlet",
       
        "sling.servlet.paths=" + "/bin/login"
    })
public class WorkFlowServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Perform validation or authentication logic here
        if (isValidUser(email, password)) {
            response.setStatus(SlingHttpServletResponse.SC_OK);
            response.getWriter().write("Login successful");
        } else {
            response.setStatus(SlingHttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid email or password");
        }
    }

    private boolean isValidUser(String email, String password) {
        // Replace this with your own authentication logic
        return "test@example.com".equals(email) && "password123".equals(password);
    }
}
