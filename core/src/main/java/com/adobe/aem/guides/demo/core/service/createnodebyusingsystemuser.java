package com.adobe.aem.guides.demo.core.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = createnodebyusingsystemuser.class,immediate = true)
public class createnodebyusingsystemuser {

	private static final Logger log=LoggerFactory.getLogger(createnodebyusingsystemuser.class);
	
	@Reference
	 ResourceResolverFactory resourceResolverFactory;
	@Activate
	public void init() throws IOException
	{
		log.info("Node has created");
		
			createNode();
	}
	
	@Deactivate
	public void deactivate() throws IOException
	{
		log.info("Starting execution");
		createNode();
		log.info("node has executed");
	}
	 public void createNode() throws IOException
	 {
		
		try {
			ResourceResolver resolver = createsystemuser.newResolver(resourceResolverFactory);
			Resource resource = resolver.getResource("/content/Demo/jcr:content");
			Map<String, Object> map =  new HashMap<String, Object>();
			map.put("title", "Hemanth");
			map.put("id", "6309");
			map.put("dob", "2001-05-27");
			resolver.create(resource, "Node10", map);
			resolver.commit();
			
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
