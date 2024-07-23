package com.example.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.framework.Constants;

import javax.servlet.Servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

@Component(service = Servlet.class,
        property = {
                Constants.SERVICE_DESCRIPTION + "=Data Fetch Servlet",
                "sling.servlet.methods={GET,POST}",
                "sling.servlet.paths=/bin/fetchdata"
        })
public class DataFetchServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        String path = "/content/Demo/us/en/form";

        Resource resource = resourceResolver.getResource(path);
        if (resource != null) {
            StringBuilder tableHtml = new StringBuilder();
            tableHtml.append("<table border='1'>");
            tableHtml.append("<tr><th>Name</th><th>Email</th><th>Subject</th><th>Message</th><th>Edit</th><th>Delete</th></tr>");

            for (Resource child : resource.getChildren()) {
                String childPath = child.getPath();
                ModifiableValueMap properties = child.adaptTo(ModifiableValueMap.class);
                String name = properties.get("name", String.class);
                String email = properties.get("mail", String.class);
                String subject = properties.get("subject", String.class);
                String message = properties.get("message", String.class);

                tableHtml.append("<tr>")
                        .append("<td>").append(name).append("</td>")
                        .append("<td>").append(email).append("</td>")
                        .append("<td>").append(subject).append("</td>")
                        .append("<td>").append(message).append("</td>")
                        .append("<td><button onclick=\"editEntry('").append(childPath).append("')\">Edit</button></td>")
                        .append("<td><button onclick=\"deleteEntry('").append(childPath).append("')\">Delete</button></td>")
                        .append("</tr>");
            }

            tableHtml.append("</table>");
            response.setContentType("text/html");
            response.getWriter().write(tableHtml.toString());
        } else {
            response.getWriter().write("No data found at the specified path.");
        }
    }

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        String action = request.getParameter("action");
        String nodePath = request.getParameter("nodePath");

        if ("edit".equals(action)) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String subject = request.getParameter("subject");
            String message = request.getParameter("message");

            Resource resource = resourceResolver.getResource(nodePath);
            if (resource != null) {
                ModifiableValueMap properties = resource.adaptTo(ModifiableValueMap.class);
                properties.put("name", name);
                properties.put("mail", email);
                properties.put("subject", subject);
                properties.put("message", message);
                resourceResolver.commit();
                response.getWriter().write("Entry updated successfully.");
            } else {
                response.getWriter().write("Failed to update entry.");
            }
        } else if ("delete".equals(action)) {
            Resource resource = resourceResolver.getResource(nodePath);
            if (resource != null) {
                resourceResolver.delete(resource);
                resourceResolver.commit();
                response.getWriter().write("Entry deleted successfully.");
            } else {
                response.getWriter().write("Failed to delete entry.");
            }
        }
    }
}
