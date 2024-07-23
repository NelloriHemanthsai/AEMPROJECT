package com.adobe.aem.guides.demo.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CrxdetomModel {
private static final Logger log = LoggerFactory.getLogger(crxdetomodel.class);
	
	@Inject
	private String firstname;
	
	@ScriptVariable
	Resource resource;
	
	@ValueMapValue
	private String lastname;
	
	@ChildResource
	Resource multifield;
	@PostConstruct
	public void init()
	{
		log.info("slingmodel is executed");
	}
	
	public String getFirstName()
	{
		return firstname;
	}
	
	public String getLastname()
	{
		return lastname;
	}

}
