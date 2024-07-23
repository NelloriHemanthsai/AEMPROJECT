package com.adobe.aem.guides.demo.core.models;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.jcr.query.Query;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
		adaptables = {
				Resource.class,SlingHttpServletRequest.class},
	
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
		)

public class InThisCategory {
	
	@ValueMapValue
	private String title;
	
	@ScriptVariable
	ValueMap pageProperties;
	
	@SlingObject
	ResourceResolver resolver;
	
	List<ArticleDetailModel> articleList;
	
	@PostConstruct
	public void init()
	{
		if (pageProperties != null) {
			String[] tags = pageProperties.get("cq:tags",String[].class);
			String categoryTag = findCategoryTag(tags);
			String que = "SELECT * FROM [cq:Page] AS s WHERE ISDESCENDANTNODE([/content/Demo]) and s.[jcr:content/cq:tags] like '"+categoryTag+"%'";
			Iterator<Resource> result = resolver.findResources(que, Query.JCR_SQL2);
			while (result.hasNext()) {
				Resource resource = result.next();
				Resource articleResource = resolver.getResource(resource.getPath()+"/jcr:content/root/container/article_grid/left-container/article_details");
				if (articleResource !=null) {
					ArticleDetailModel articleModel = articleResource.adaptTo(ArticleDetailModel.class);
					if (articleModel !=null) 
						articleList.add(articleModel);
					
				}
			}
		}
	}


	private String findCategoryTag(String[] tags) {
		for(String tag: tags){
			if (tag.startsWith("newsportal:categories")) {
				String[] tagItems = tag.split("/");
				if (tagItems.length>=2) {
					return tagItems[0]+"/"+tagItems[1];
				}
			}
		}
		return null;
		// TODO Auto-generated method stub
		
	}


	public String getTitle() {
		return title;
	}


	public List<ArticleDetailModel> getArticleList() {
		return articleList;
	}


	

}
