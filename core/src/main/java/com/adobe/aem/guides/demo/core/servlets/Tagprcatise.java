package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

@Component(service = Servlet.class,immediate = true,
property = "sling.servlet.paths=/bin/tag/exam")
public class Tagprcatise extends SlingAllMethodsServlet{
	
	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		ResourceResolver resolver = req.getResourceResolver();
		TagManager tagManager = resolver.adaptTo(TagManager.class);
		Tag tag = tagManager.resolve("/content/cq:tags/hemanthtags/color");
		Iterator<Tag> children = tag.listChildren();
		JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		while (children.hasNext()) {
			Tag next = children.next();
			jsonObjectBuilder.add("Tittle", next.getTitle());
			jsonObjectBuilder.add("Path", next.getPath());
			arrayBuilder.add(jsonObjectBuilder);
			res.getWriter().write(arrayBuilder.build().toString());
		}
	}

}
