package com.mdis.testproject.projects.model;

public class Project {
	
	private String id;
	private String name;
	private String html_url;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	
	public Project() {}
	
	public Project(String id, String name, String html_url) {
		super();
		this.id = id;
		this.name = name;
		this.html_url = html_url;
	}
	
	

}
