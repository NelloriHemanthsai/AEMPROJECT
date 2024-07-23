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
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;

@Component(service = Servlet.class,immediate = true,
property = "sling.servlet.paths=/bin/wf/example")
public class Workflowpractise2 extends SlingAllMethodsServlet{
	String state="";
	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res) throws IOException
	{
		ResourceResolver resolver = req.getResourceResolver();
		WorkflowSession workflowSession = resolver.adaptTo(WorkflowSession.class);
		try {
			WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/hemanth_practise-pagecreation");
			WorkflowData payload = workflowSession.newWorkflowData("JCR_PATH", "/content/we-retail/ca/fr");
		  state = workflowSession.startWorkflow(workflowModel, payload).getState();
			res.getWriter().write("workflow is executed successfully...{}"+state);
		} catch (WorkflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
