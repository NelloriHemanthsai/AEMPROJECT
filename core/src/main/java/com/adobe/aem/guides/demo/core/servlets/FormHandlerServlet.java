package com.adobe.aem.guides.demo.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.Servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(service = { Servlet.class }, property = {
    "sling.servlet.methods=POST",
    "sling.servlet.methods=GET",
    "sling.servlet.paths=/bin/your-project/form-handler"
})
@ServiceDescription("Form Handler Servlet")
public class FormHandlerServlet extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(FormHandlerServlet.class);
    private static final String DATA_PATH = "/content/Demo/us/en";
    private static final String FORM_NODE_NAME = "form";

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String mail = request.getParameter("mail");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        ResourceResolver resourceResolver = null;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put(ResourceResolverFactory.SUBSERVICE, "hemanth");
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(params);

            Session session = resourceResolver.adaptTo(Session.class);
            if (session != null) {
                Node rootNode = session.getNode(DATA_PATH);
                Node formNode;
                if (!rootNode.hasNode(FORM_NODE_NAME)) {
                    formNode = rootNode.addNode(FORM_NODE_NAME, "nt:unstructured");
                } else {
                    formNode = rootNode.getNode(FORM_NODE_NAME);
                }
                int itemCount = 0;
                if (formNode.hasNodes()) {
                    itemCount = (int) formNode.getNodes().getSize();
                }
                Node itemNode = formNode.addNode("item" + itemCount, "nt:unstructured");
                itemNode.setProperty("name", name);
                itemNode.setProperty("mail", mail);
                itemNode.setProperty("subject", subject);
                itemNode.setProperty("message", message);
                session.save();
                
                // Write a response indicating that the node was created
                response.getWriter().write("Form submitted successfully! Node 'item" + itemCount + "' created under 'form'.");
            }
        } catch (Exception e) {
            LOG.error("Error saving form data to JCR", e);
            response.getWriter().write("Error submitting form: " + e.getMessage());
        } finally {
            if (resourceResolver != null) {
                resourceResolver.close();
            }
        }
    }

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        ResourceResolver resourceResolver = null;
        JSONArray formDataArray = new JSONArray();
        try {
            Map<String, Object> params = new HashMap<>();
            params.put(ResourceResolverFactory.SUBSERVICE, "hemanth");
            resourceResolver = resourceResolverFactory.getServiceResourceResolver(params);

            Resource resource = resourceResolver.getResource(DATA_PATH + "/" + FORM_NODE_NAME);
            if (resource != null) {
                for (Resource child : resource.getChildren()) {
                    ModifiableValueMap properties = child.adaptTo(ModifiableValueMap.class);
                    if (properties != null) {
                        JSONObject formData = new JSONObject();
                        formData.put("name", properties.get("name", String.class));
                        formData.put("mail", properties.get("mail", String.class));
                        formData.put("subject", properties.get("subject", String.class));
                        formData.put("message", properties.get("message", String.class));
                        formDataArray.put(formData);
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Error retrieving form data from JCR", e);
        } finally {
            if (resourceResolver != null) {
                resourceResolver.close();
            }
        }

        response.setContentType("application/json");
        response.getWriter().write(formDataArray.toString());
    }
}
