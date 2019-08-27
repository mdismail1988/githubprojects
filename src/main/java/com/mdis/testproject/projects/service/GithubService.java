package com.mdis.testproject.projects.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.mdis.testproject.projects.model.Project;

@Service
public class GithubService {
	
	private final RestTemplate restTemplate;
	private final String githubUserProjectsApiUrl="https://api.github.com/users/";
	private final String githubProjectReadmeUrl="https://raw.githubusercontent.com/";
	
	public GithubService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public List<Project> getUserProjects(String username) {
		List<Project> projects = new ArrayList<Project>();
		try {
			String output = this.restTemplate.getForObject(githubUserProjectsApiUrl+"{username}/repos", String.class, username);
			Gson gson = new Gson();
			JsonArray body = gson.fromJson(output, JsonArray.class);
			for(int i=0;i<body.size();i++) {
				Project project = new Project();
				project.setId(body.get(i).getAsJsonObject().get("id").getAsString());
				project.setName(body.get(i).getAsJsonObject().get("name").getAsString());
				project.setHtml_url(body.get(i).getAsJsonObject().get("html_url").getAsString());
				projects.add(project);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return projects;
	}
	
	public String getProjectReadme(String username,String projectName) {
		String readmeContent;
		String output = this.restTemplate.getForObject(githubProjectReadmeUrl+"{username}/{projectName}/master/README.md", String.class, username,projectName);
		readmeContent = output.substring(0,50);
		return readmeContent;
	}

}
