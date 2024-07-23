package com.adobe.aem.guides.demo.core.servlets;

import com.adobe.aem.guides.demo.core.models.CourseItem;
import com.google.gson.Gson;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_PATHS;

@Component(
        service = {Servlet.class},
        property = {
                SLING_SERVLET_PATHS + "=/bin/demo/courses"
        }
)
public class CourseInfoServlet extends SlingAllMethodsServlet {
    private static final Logger log = LoggerFactory.getLogger(CourseInfoServlet.class);

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        ResourceResolver resourceResolver = request.getResourceResolver();
        Resource multifieldResource = resourceResolver.getResource("/content/Demo/us/en/jcr:content/root/apicomponent/multifield");

        if (multifieldResource != null) {
            List<CourseItem> courseItems = new ArrayList<>();
            for (Resource itemResource : multifieldResource.getChildren()) {
                CourseItem courseItem = itemResource.adaptTo(CourseItem.class);
                if (courseItem != null) {
                    courseItems.add(courseItem);
                }
            }

            String jsonResponse = new Gson().toJson(courseItems);
            response.setContentType("application/json");
            response.getWriter().write(jsonResponse);
        } else {
            response.setStatus(SlingHttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write("Multifield resource not found.");
        }
    }
}
