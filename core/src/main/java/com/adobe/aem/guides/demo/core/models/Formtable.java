package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Formtable {

	@ValueMapValue
	public String name;
	
	
	@ValueMapValue
	public String email ;
	
	
	@ValueMapValue
	public String subject;
	
	
	@ValueMapValue
	public String message;


	public String getName() {
		return name;
	}


	public String getEmail() {
		return email;
	}


	public String getSubject() {
		return subject;
	}


	public String getMessage() {
		return message;
	}
}
