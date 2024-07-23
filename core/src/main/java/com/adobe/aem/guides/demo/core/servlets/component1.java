package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
@Component(service = Servlet.class,immediate = true,property = {
		"sling.servlet.paths=/bin/demo/examples"
})
public class component1 extends SlingAllMethodsServlet{
public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws PersistenceException
{
	try {
	String first = req.getParameter("first");
	String second = req.getParameter("second");
	String email = req.getParameter("email");
	ResourceResolver resolver = req.getResourceResolver();
	Resource parentresource = resolver.getResource("/content/Demo/us/jcr:content");
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("first", first);
	map.put("second", second);
	map.put("email", email);
	resolver.create(parentresource, "Node21", map );
		res.getWriter().write("jcr:Node is created");
		resolver.commit();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		try {
			res.getWriter().write("getting error");
		} catch (IOException f) {
			// TODO: handle exception
			f.printStackTrace();
		}
	}
}
}
