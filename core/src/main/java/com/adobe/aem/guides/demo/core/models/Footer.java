package com.adobe.aem.guides.demo.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Footer {
	
	@ValueMapValue
	public String title;
	
	
	@ValueMapValue
	public String description;
	
	
	@ChildResource
	public List<QuickLinks> quicklinks;
	
	
	@ChildResource
	public List<Sociallinks> sociallinks;


	public String getTitle() {
		return title;
	}


	public String getDescription() {
		return description;
	}


	public List<QuickLinks> getQuicklinks() {
		return quicklinks;
	}


	public List<Sociallinks> getSociallinks() {
		return sociallinks;
	}
	
	

}
