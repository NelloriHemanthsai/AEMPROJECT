package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class,immediate = true,
property = {
		"sling.servlet.paths=/bin/example/servlet",
//		"sling.servlet.extensions=json",
//		"sling.servlet.selector=one"
})
public class servlettwopractise extends SlingAllMethodsServlet{
	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		JsonObjectBuilder jsonObject = Json.createObjectBuilder();
		jsonObject.add("name", "hemanth");
		jsonObject.add("url", "www.surge.com");
		jsonObject.add("company", "oak");
		res.getWriter().write(jsonObject.build().toString());
	}

}
