package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component(service = Servlet.class,immediate = true,
property = "sling.servlet.paths=/bin/jcrsql/example")
public class jcrsqltwo extends SlingAllMethodsServlet{
	String queries ="SELECT page.* FROM [cq:Page] AS page\r\n" + 
			"INNER JOIN [cq:PageContent] AS jcrContentNode ON ISCHILDNODE(jcrContentNode, page)\r\n" + 
			"WHERE ISDESCENDANTNODE(page, \"/content/we-retail\")\r\n" + 
			"AND jcrContentNode.[cq:lastModified] <= CAST(\"2023-01-01T00:00:00.000+00:00\" AS DATE)";
	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		try {
		ResourceResolver resolver = req.getResourceResolver();
		Session session = resolver.adaptTo(Session.class);
			QueryManager queryManager = session.getWorkspace().getQueryManager();
			 Query createQuery = queryManager.createQuery(queries,Query.JCR_SQL2);
			 QueryResult result = createQuery.execute();
			 NodeIterator nodes = result.getNodes();
			 List<String> paths = new ArrayList();
			 while (nodes.hasNext()) {
				Node nextNode = nodes.nextNode();
				String path = nextNode.getPath();
				paths.add(path);
			}
			 JsonObject jsonResponse = new JsonObject();
	            JsonArray resultsArray = new JsonArray();
			 for(String var:paths)
			 {
				 resultsArray.add(var);
			 }
			 jsonResponse.add("result",resultsArray);
//			 res.getWriter().write(jsonResponse.toString());
			 PrintWriter writer = res.getWriter();
	            writer.print(jsonResponse.toString());
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
