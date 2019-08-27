package com.mdis.testproject.projects.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mdis.testproject.projects.model.Project;
import com.mdis.testproject.projects.service.GithubService;

@RequestMapping("/projects")
@RestController
public class ProjectController {
	
	@Autowired
	private GithubService githubService;
	
	@RequestMapping(method=RequestMethod.GET, value = "/{username}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public  List<Project> getProjectListByUserName(@PathVariable("username") String username) {
		return  githubService.getUserProjects(username);
    }
	
	@RequestMapping(method=RequestMethod.GET, value = "/{username}/{projectName}", produces = MediaType.APPLICATION_JSON_VALUE) 
	public String getProject(@PathVariable("username") String username,@PathVariable("projectName") String projectName) {
		return githubService.getProjectReadme(username, projectName);
    }


}
