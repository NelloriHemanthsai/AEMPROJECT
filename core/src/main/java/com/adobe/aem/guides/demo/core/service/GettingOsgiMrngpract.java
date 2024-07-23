package com.adobe.aem.guides.demo.core.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = GettingOsgiMrngpract.class,immediate = true)
public class GettingOsgiMrngpract {
	private static final Logger log=LoggerFactory.getLogger(GettingOsgiMrngpract.class);
	@Reference
	ResourceResolverFactory resourceResolverFactory;
	@Activate
	public void Activate()
	{
		createNode();
		log.info("node is created");
	}
	@Deactivate
	public void Deactivate()
	{
		log.info("starting execution");
		createNode();
		log.info("node has created");
	}
	public void createNode()
	{
		try {
			ResourceResolver resourceResolver = OsgimrngPractise.newResolver(resourceResolverFactory);
			Resource resource = resourceResolver.getResource("/content/Demo/jcr:content");
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("name", "hemanth");
			map.put("surname", "nellori");
			map.put("lastname", "sai");
			resourceResolver.create(resource, "Node15", map);
			resourceResolver.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
