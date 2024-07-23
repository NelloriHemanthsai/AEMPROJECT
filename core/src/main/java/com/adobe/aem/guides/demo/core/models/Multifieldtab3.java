package com.adobe.aem.guides.demo.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class Multifieldtab3 {

	
	@ValueMapValue
	private String desk;
	
	
	@ValueMapValue
	private String mob;
	
	
	@ChildResource
	public List<Nestedtab3> navi;


	public String getDesk() {
		return desk;
	}


	public String getMob() {
		return mob;
	}


	public List<Nestedtab3> getNavi() {
		return navi;
	}


}