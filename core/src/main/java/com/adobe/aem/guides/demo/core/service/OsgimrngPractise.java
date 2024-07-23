package com.adobe.aem.guides.demo.core.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

public class OsgimrngPractise {
	private OsgimrngPractise()
	{
		
	}
	public static final String SYSTEM_SERVICE="Oaks";
	public static ResourceResolver newResolver(ResourceResolverFactory resourceResolverFactory) throws Exception
	{
		Map<String, Object> map =  new HashMap<String, Object>();
		map.put(ResourceResolverFactory.SUBSERVICE, SYSTEM_SERVICE);
		ResourceResolver resourceResolver = resourceResolverFactory.getServiceResourceResolver(map);
		return resourceResolver;
		
	}

}
