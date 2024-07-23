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

@Component(service = createnodeosgipractise.class,immediate = true)
public class createnodeosgipractise {
	@Reference
	ResourceResolverFactory rrf;

	private static final Logger log=LoggerFactory.getLogger(createnodeosgipractise.class);

	
	@Activate
	public void activate() 
	{
		createnode();
	}
	
	@Deactivate
	public void deactivate() 
	{
		createnode();
	}
	public void createnode()
	{
		ResourceResolver resourceResolver;
		try {
			resourceResolver = systemuserpractise.getResourceResolver(rrf);
			Resource resource = resourceResolver.getResource("/content/Demo/us/en/jcr:content/root");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", "hemanth");
			map.put("surname", "nellori");
			resourceResolver.create(resource, "node15", map);
			resourceResolver.commit();
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
