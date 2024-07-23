package com.adobe.aem.guides.demo.core.models;

import java.util.List;

//import javax.annotation.Resource;
import org.apache.sling.api.resource.Resource;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL

		)

public class aboutMySelf {
	
	
	@ValueMapValue
	public String hobbies;
	
	

	@ChildResource
	public List<nest> nested;
	
	
	@ValueMapValue
	public String joiningdate;

	public String getHobbies() {
		return hobbies;
	}



	public String getJoiningdate() {
		return joiningdate;
	}

	
	public List<nest> getNested() {
		return nested;
	}
}
