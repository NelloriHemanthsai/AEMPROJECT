package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class,immediate = true)
@SlingServletResourceTypes(resourceTypes = {"/bin/hemanth/siva"})
public class Hemanthservlet extends SlingAllMethodsServlet {
	public void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws IOException
	{
		res.setContentType("application/json");
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		jsonObjectBuilder.add("firstname","Hemanth");
		jsonObjectBuilder.add("lastname","sai");
		jsonObjectBuilder.add("companyName","Surgesoftware");
		jsonObjectBuilder.add("url","www.surge.com");
		
		res.getWriter().write(jsonObjectBuilder.build().toString());
	}

}
