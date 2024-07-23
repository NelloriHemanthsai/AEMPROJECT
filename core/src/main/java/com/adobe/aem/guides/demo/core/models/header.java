package com.adobe.aem.guides.demo.core.models;

import java.util.List;

import javax.annotation.PostConstruct;

//import javax.annotation.Resource;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class header {
	
	private static final Logger log=LoggerFactory.getLogger(Finaltogetproperties.class);
	
	@ValueMapValue
	private String path;
	
	@ValueMapValue
	private String lastname;
	
	@ValueMapValue
	private String checkbox;
	
	
	@ChildResource
	public List<multifield> multifield;

	
	@PostConstruct
	public void init() {
		log.info("all the properties get successfully");
	}
	
	public String getPath()
	{
		return path;
	}
	
	
	public String getLastname()
	{
		return lastname;
	}
	
	public String getCheckbox()
	{
		return checkbox;
	}
	
	public List<multifield> getMultifield() {
		return multifield;
	}


	

}
