package com.adobe.aem.guides.demo.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class Dam1 {

	
	@ValueMapValue
	public String text;
	
	@ValueMapValue
	public String path;

	public String getText() {
		return text;
	}

	public String getPath() {
		return path;
	}
	
	@ChildResource
	public List<Dam2> Dam2;

	public List<Dam2> getDam2() {
		return Dam2;
	}
	
	
	
	
	
	
	
	
	
	
}
