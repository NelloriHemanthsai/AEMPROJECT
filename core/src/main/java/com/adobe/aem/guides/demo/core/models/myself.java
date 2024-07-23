package com.adobe.aem.guides.demo.core.models;

import java.util.List;
import javax.inject.Inject;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class myself {

    @ValueMapValue
    private String headone;

    @ValueMapValue
    private String headpath;

    @ChildResource
    private List<nestedmulti> nested;

  

    public String getHeadone() {
		return headone;
	}



	public String getHeadpath() {
		return headpath;
	}



	public List<nestedmulti> getNested() {
        return nested;
    }
}
