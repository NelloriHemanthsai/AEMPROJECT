package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;


@Component(service = Servlet.class)
@SlingServletPaths(value = {"/bin/appse/demosa"})
public class Demotwo extends SlingAllMethodsServlet {
	
	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException {
		String parameter = req.getParameter("session");
		
		if (parameter != null) {
			res.setContentType("text/plain");
			PrintWriter writer = res.getWriter();
			String randomWord = generateRandomWord(6);
			writer.write(randomWord);
		} else {
			res.getWriter().write("your giving wrong parameter");
		}
	}
	
	public String generateRandomWord(int length) {
		String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		 StringBuilder random = new StringBuilder();
		 Random rand = new Random();
		 for(int i=0;i<length;i++)
		 {
			 int index = rand.nextInt(allowedCharacters.length());
			 random.append(allowedCharacters.charAt(index));
		 }
		 return random.toString();
		
	}

}
