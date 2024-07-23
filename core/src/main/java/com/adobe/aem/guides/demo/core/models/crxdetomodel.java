package com.adobe.aem.guides.demo.core.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class crxdetomodel {
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
	
//	public List<Map<String, Object>> getMultifield()
//	{
//		 List<Map<String, Object>> mainmap=new ArrayList<Map<String,Object>>();
//		 if (multifield!=null) {
//			 Map<String, Object> childmap=new HashMap<String, Object>();
//			 for (Resource map : multifield.getChildren()) {
//				 childmap.put("phonenumber", map.getValueMap().get("phonenumber", String.class));
//				 childmap.put("pincode", map.getValueMap().get("pincode", String.class));
//				 mainmap.add(childmap);
//			}
//		}
//		return mainmap;
//		
//	}
	

}
