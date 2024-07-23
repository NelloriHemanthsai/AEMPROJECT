package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(service = Servlet.class,immediate = true,
property = {
		"sling.servlet.paths=/bin/query/example"
})
public class querybuilder extends SlingAllMethodsServlet{

	@Reference
	QueryBuilder qb;
	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		
		
		ResourceResolver resourceResolver = req.getResourceResolver();
		Session session = resourceResolver.adaptTo(Session.class);
		Map<String, Object> map =  new HashMap<String, Object>();
		map.put("type", "cq:Page");
		map.put("path","/content/we-retail" );
		
		Query createQuery = qb.createQuery(PredicateGroup.create(map), session);
		SearchResult result = createQuery.getResult();
		List<Hit> hits = result.getHits();
		Iterator<Hit> iterator = hits.iterator();
		JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
		while (iterator.hasNext()) {
			try {
//				JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
				Hit next = iterator.next();
				jsonObjectBuilder.add("title", next.getTitle().toString());
				jsonObjectBuilder.add("path", next.getPath().toString());
				res.getWriter().write(jsonObjectBuilder.build().toString());
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
