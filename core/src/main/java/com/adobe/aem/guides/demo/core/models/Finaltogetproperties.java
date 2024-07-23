package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Finaltogetproperties {
private static final Logger log=LoggerFactory.getLogger(Finaltogetproperties.class);
	@Inject
	private String email;
	
	@ValueMapValue
	private String text;
	
	
	@ValueMapValue
	@Default(values = "practical")
	private String username;
	
	@ValueMapValue
	@Required
	private String password;
	
	@ValueMapValue
	@Named("sling:resourceType")
	private String slingresourceType;
	@PostConstruct
	public void init()
	{
		log.info("all the properties get successfully");
	}
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	
	@ChildResource
	Resource multifield;
	
	public List<Map<String, Object>> getMultifield()
	{
		List<Map<String, Object>> mainmap=new ArrayList<Map<String,Object>>();
		
		if (multifield!=null) {
			Map<String, Object> childmap=new HashMap<String, Object>();
			
			for(Resource childresource:multifield.getChildren()) {
				childmap.put("address",childresource.getValueMap().get("address",String.class));
				childmap.put("currentaddress",childresource.getValueMap().get("currentaddress",String.class));
				childmap.put("pincode",childresource.getValueMap().get("pincode",String.class));
				mainmap.add(childmap);
			}
		}
		return mainmap;
	}
	
	
	
}
