package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class,},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class hello {

	
	@ScriptVariable
	Resource resource;
	
	@ValueMapValue
	private String email;
	
	
	public String getEmail()
	{
		return email;
	}
	
}
