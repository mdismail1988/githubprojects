package com.teletronics.testproject.projects;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mdis.testproject.projects.App;
import com.mdis.testproject.projects.model.Project;
import com.mdis.testproject.projects.service.GithubService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class ProjectTest {
	
	  @Autowired
	  private WebApplicationContext webApplicationContext;
	  private MockMvc mockMvc;

	  @Before
	  public void setUp() {
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	  }
	
	  @MockBean
	  private GithubService githubService;
	 
	  Project mockProject = new Project("203295920","purchaseInfoService","https://github.com/mdismail1988/purchaseInfoService");
	 
	 
	  String sampleProjectJson = "[{\"id\":\"203295920\",\"name\":\"purchaseInfoService\",\"html_url\":\"https://github.com/mdismail1988/purchaseInfoService\"}]";
	 
	  @Test
	  public void getProjectListByUserNameTest() throws Exception {
		 List<Project> mockProjects = new ArrayList<Project>();
		 mockProjects.add(mockProject);
		 
		 Mockito.when(githubService.getUserProjects("mdismail1988")).thenReturn(mockProjects);
		 RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/projects/mdismail1988").accept(MediaType.APPLICATION_JSON);
		 MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 System.out.println(result.getResponse());
		 JSONAssert.assertEquals(sampleProjectJson, result.getResponse().getContentAsString(), false);
	  }

}
