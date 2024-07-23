package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class)
@SlingServletPaths(value = { "/bin/siva/fg" })
public class SivaServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws ServletException, IOException {
        String damPath = "/content/dam/capstone/logohori.jpeg";
        ResourceResolver resolver = req.getResourceResolver();

        try {
            Resource resource = resolver.getResource(damPath + "/jcr:content/renditions/original");
            if (resource != null) {
                InputStream imageStream = resource.adaptTo(InputStream.class);
                if (imageStream != null) {
                    String extension = FilenameUtils.getExtension(damPath);
                    String contentType = getContentType(extension);
                    if (contentType != null) {
                        res.setContentType(contentType);
                        IOUtils.copy(imageStream, res.getOutputStream());
                        res.getOutputStream().flush();
                    } else {
                        res.sendError(SlingHttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
                    }
                } else {
                    res.sendError(SlingHttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                res.sendError(SlingHttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            res.sendError(SlingHttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private String getContentType(String extension) {
        switch (extension.toLowerCase()) {
            case "png":
                return "image/png";
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "gif":
                return "image/gif";
            case "bmp":
                return "image/bmp";
            case "svg":
                return "image/svg+xml";
            case "webp":
                return "image/webp";
            default:
                return null;
        }
    }
}
