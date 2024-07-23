package com.adobe.aem.guides.demo.core.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

public class systemuserpractise {
	private systemuserpractise()
	{
		
	}
	public static final String SYSTEM_SERVICE="systemuser";
	
	public static ResourceResolver getResourceResolver(ResourceResolverFactory rrf) throws LoginException
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(ResourceResolverFactory.SUBSERVICE, SYSTEM_SERVICE);
		ResourceResolver resourceResolver = rrf.getResourceResolver(map);
		return resourceResolver;
		
	}

}
