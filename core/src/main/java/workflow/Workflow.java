package workflow;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.demo.core.service.EmailService;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, property = {"process.label=Send Email Process"})
public class Workflow implements WorkflowProcess {
	 private static final Logger log = LoggerFactory.getLogger(Workflow.class);
	   @Reference
	    private EmailService emailService;

	    @Override
	    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap args) {
	        try {
	        	log.info("this is process step by hemanth");
	            String pagePath = workItem.getWorkflowData().getPayload().toString();
	            emailService.sendEmail("hemanth.nellori@gmail", "Page Published", "A page has been published: " + pagePath);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
