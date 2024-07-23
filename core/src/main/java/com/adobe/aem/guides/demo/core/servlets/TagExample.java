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

@Component(service=Servlet.class,immediate = true,
property= {
		"sling.servlet.paths=/bin/example/tag"
})
public class TagExample extends SlingAllMethodsServlet{

	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		ResourceResolver resolver = req.getResourceResolver();
		TagManager tagManager = resolver.adaptTo(TagManager.class);
		Tag tag = tagManager.resolve("/content/cq:tags/project");
		Iterator<Tag> listChildren = tag.listChildren();
		JsonArrayBuilder jsonArray = Json.createArrayBuilder();
		JsonObjectBuilder jsonObject = Json.createObjectBuilder();
		while(listChildren.hasNext())
		{
			Tag childtag = listChildren.next();
			jsonObject.add("title",childtag.getTitle());
			jsonObject.add("Path",childtag.getPath());
			jsonObject.add("count", childtag.getCount());
			jsonArray.add(jsonObject);
			res.getWriter().write(jsonArray.build().toString());
		}
		
	}
}
