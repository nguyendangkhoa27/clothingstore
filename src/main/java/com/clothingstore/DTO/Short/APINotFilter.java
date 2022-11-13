package com.clothingstore.DTO.Short;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;

public class APINotFilter {
	private String servletPath;
	private HttpMethod httpMethod;
	List<APINotFilter> list;
	public APINotFilter(String servletPath, HttpMethod httpMethod) {
		super();
		this.servletPath = servletPath;
		this.httpMethod = httpMethod;
		if(this.list == null) {
			this.list = new ArrayList<>();
			this.list.add(this);
		}
	}
	
	public APINotFilter addAPI(String servletPath, HttpMethod httpMethod){
		APINotFilter api = new APINotFilter(servletPath, httpMethod);
		api.setList(null);
		list.add(api);
	return this;
}
	public APINotFilter addAPI(String servletPath){
		APINotFilter api = new APINotFilter(servletPath, null);
		api.setList(null);
		list.add(api);
		return this;
	}
	public List<APINotFilter> getList() {
		return list;
	}

	public void setList(List<APINotFilter> list) {
		this.list = list;
	}

	public String getServletPath() {
		return servletPath;
	}
	public void setServletPath(String servletPath) {
		this.servletPath = servletPath;
	}
	public HttpMethod getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}
	
	
}
