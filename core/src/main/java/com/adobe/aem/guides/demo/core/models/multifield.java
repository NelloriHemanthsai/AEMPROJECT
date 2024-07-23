package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import org.apache.sling.api.resource.Resource;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class multifield {
	
	@ValueMapValue
	public String hobbies;
	
	@ValueMapValue
	public String jd;
	
	
	
	public String getHobbies()
	{
		return hobbies;
	}
	
	public String getJd()
	{
		return jd;
	}



}
