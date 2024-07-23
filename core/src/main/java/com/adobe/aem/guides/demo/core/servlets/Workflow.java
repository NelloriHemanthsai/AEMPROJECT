package com.adobe.aem.guides.demo.core.servlets;

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
property= {
		"sling.servlet.paths=/bin/demo/exampleservlet"
})
public class Workflow extends SlingAllMethodsServlet{
	
	public void doGet(SlingHttpServletRequest req,SlingHttpServletResponse res)
	{
		String state="";
		try {
		res.setContentType("text/plain");
		ResourceResolver resolver = req.getResourceResolver();
		WorkflowSession workflowSession = resolver.adaptTo(WorkflowSession.class);
	
			WorkflowModel Model = workflowSession.getModel("/var/workflow/models/hemanth_page_creation");
			WorkflowData payload = workflowSession.newWorkflowData("JCR_PATH", "/content/Demo/us");
			state = workflowSession.startWorkflow(Model,payload).getState();
			res.getWriter().write("Work flow executed sucessfully.. {}"+state);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
			res.getWriter().write("Workflow failed");
			}
			catch(Exception f)
			{
				f.printStackTrace();
			}
		}
	}

}
