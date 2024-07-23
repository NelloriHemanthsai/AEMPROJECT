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
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Component(service = Servlet.class,immediate = true,
	property = "sling.servlet.paths=/bin/sqltwos/practise"
		)
public class Jcrsql2practise extends SlingAllMethodsServlet{
	String queryString = "SELECT page.* FROM [cq:Page] AS page "
            + "INNER JOIN [cq:PageContent] AS jcrContentNode ON ISCHILDNODE(jcrContentNode, page) "
            + "WHERE ISDESCENDANTNODE(page, '/content/we-retail') "
            + "AND jcrContentNode.[cq:lastModified] <= CAST('2023-01-01T00:00:00.000+00:00' AS DATE)";
	
	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		Session session = req.getResourceResolver().adaptTo(Session.class);
		try {
			QueryManager queryManager = session.getWorkspace().getQueryManager();
			Query query = queryManager.createQuery(queryString, Query.JCR_SQL2);
			QueryResult result = query.execute();
			NodeIterator nodes = result.getNodes();
			List<String> pathsarray = new ArrayList<String>();
			while (nodes.hasNext()) {
				Node nextNode = nodes.nextNode();
				String path = nextNode.getPath();
				pathsarray.add(path);
			}
			 JsonObject jsonResponse = new JsonObject();
	           JsonArray jsonarray=new JsonArray();
	            
	            for (String var : pathsarray) {
					jsonarray.add(var);
				}
	            jsonResponse.add("results", jsonResponse);
	            
	            PrintWriter writer = res.getWriter();
	            writer.write(jsonResponse.toString());;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
