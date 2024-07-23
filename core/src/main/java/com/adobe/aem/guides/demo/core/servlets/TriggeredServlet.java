package com.adobe.aem.guides.demo.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.osgi.service.component.propertytypes.ServiceVendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import java.io.IOException;

@Component(service = { Servlet.class }, property = {
        "sling.servlet.methods=GET",
        "sling.servlet.paths=/bin/demo/triggeredServlet"
})
@ServiceDescription("Simple Demo Servlet")
@ServiceVendor("Adobe")
public class TriggeredServlet extends SlingAllMethodsServlet {

    private static final Logger log = LoggerFactory.getLogger(TriggeredServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        log.info("Triggered Servlet is invoked.");
        response.setContentType("application/json");
        // response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\": \"Servlet triggered by event handler created by hemanth.\"}");
    }
}
