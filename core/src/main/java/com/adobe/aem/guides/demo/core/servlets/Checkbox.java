package com.adobe.aem.guides.demo.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

@Component(
    service = { Servlet.class },
    property = {
        "sling.servlet.methods=POST",
        "sling.servlet.paths=/bin/deleteContentNode"
    }
)
public class Checkbox extends SlingAllMethodsServlet {

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String path = request.getParameter("path");
        if (path == null || path.isEmpty()) {
            response.setStatus(SlingHttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Path parameter is missing");
            return;
        }

        // Replace _jcr_content with jcr:content
        String modifiedPath = path.replace("_jcr_content", "jcr:content");

        // Print the received path and modified path
        response.getWriter().write("Received path: " + path + "\n");
        response.getWriter().write("Modified path: " + modifiedPath + "\n");

        ResourceResolver resolver = request.getResourceResolver();
        try {
            Resource resource = resolver.getResource(modifiedPath);
            if (resource != null) {
                ModifiableValueMap valueMap = resource.adaptTo(ModifiableValueMap.class);
                if (valueMap != null) {
                    // Remove the "cssClass" property if it exists
                    if (valueMap.containsKey("cssClass")) {
                        valueMap.remove("cssClass");
                    }
//                    resolver.delete(resource);
                    resolver.commit();
                    response.setStatus(SlingHttpServletResponse.SC_OK);
                    response.getWriter().write("Content node deleted and 'cssClass' property removed if it existed.");
                } else {
                    response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().write("Unable to adapt resource to ModifiableValueMap.");
                }
            } else {
                response.setStatus(SlingHttpServletResponse.SC_NOT_FOUND);
                response.getWriter().write("Content node not found.");
            }
        } catch (PersistenceException e) {
            response.setStatus(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("PersistenceException: " + e.getMessage());
        }
    }
}