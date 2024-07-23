package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
@Component(service = Servlet.class,immediate = true,
property = {
"sling.servlet.paths=/bin/some/demo"
})
public class storingthedata extends SlingAllMethodsServlet{
	
	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res)
	{
		try {
		String username = req.getParameter("hemu");//hemanth
		String password = req.getParameter("password");//hemanth123
		String email = req.getParameter("email");
		ResourceResolver resolver = req.getResourceResolver();
		Map<String,Object> map = new HashMap <>();
		map.put("userName", username);
		map.put("Password", password);
		map.put("Email", email);
		Resource parentResource = resolver.getResource("/content/Demo/us/en/jcr:content");
		resolver.create(parentResource, "nodehemanth", map);
		res.getWriter().write("jcr:node is created");
		resolver.commit();
		} catch (Exception e) {
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
