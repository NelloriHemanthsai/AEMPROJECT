package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service = { Servlet.class },
           property = {
              
               "sling.servlet.methods=GET",
               "sling.servlet.paths=/bin/showimage"
           })
public class ImageServlet extends SlingAllMethodsServlet {

  

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        serveImage(response);
    }

    private void serveImage(SlingHttpServletResponse response) throws IOException {
        // Adjust the path to your image
        try (InputStream imageStream = getClass().getResourceAsStream("/content/dam/capstone/forgot.png")) {
            if (imageStream != null) {
                response.setContentType("image/png");
                IOUtils.copy(imageStream, response.getOutputStream());
            } else {
                response.sendError(SlingHttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}
