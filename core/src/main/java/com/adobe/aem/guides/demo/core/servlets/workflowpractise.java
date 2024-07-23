package com.adobe.aem.guides.demo.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;

@Component(service = Servlet.class,immediate = true,
property = {
		"sling.servlet.paths=/bin/demo/example"
})
public class workflowpractise extends SlingAllMethodsServlet{
	
	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		String status;
		try {
		ResourceResolver resolver = req.getResourceResolver();
		  WorkflowSession session = resolver.adaptTo(WorkflowSession.class);
		  WorkflowModel model = session.getModel("/var/workflow/models/hemu_pagecreation");
			WorkflowData payload = session.newWorkflowData("JCR_PATH", "/content/we-retail/ca");
			status = session.startWorkflow(model, payload).getState();
			res.getWriter().write("workflow executed sucessfully{}"+status);
		} catch (WorkflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
