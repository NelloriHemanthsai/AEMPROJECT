package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ExporterConstants;

@Model(
		adaptables = {
				Resource.class,SlingHttpServletRequest.class},
	
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
		)

public class ArticleDetailModel {
	
	@ValueMapValue
	private String articleTitle;
	
	@ValueMapValue
	private String articleDesc;
	
	@ValueMapValue
	private String articleImage;

	public String getArticleTitle() {
		return articleTitle;
	}

	public String getArticleDesc() {
		return articleDesc;
	}

	public String getArticleImage() {
		return articleImage;
	}
	
//	@ValueMapValue(name = "sling:resourceType")
//	private String slingResourceType;


}
