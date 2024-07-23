package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CourseItem {

    @ValueMapValue
    private String name;

    @ValueMapValue
    private String description;
    
    

	@ValueMapValue
    private  String image;

    public String getImage() {
		return image;
	}

	public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
