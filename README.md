> Spring boot application has 2 services to get the list of projects for given username and readme.md data of the project

> The micro service will be tested & build with Docker image

> There is a Jenkins job configured for CI to trigger the build and create Docker image.

> Jenkins was installed and setup in the Amazon ec2 servers

Steps:
1.	Login the Jenkins dashboard using given url and Credentials (Shared in the email)	
2.	Build the Job “teletronics_test_Docker_image”
3.	Login the aws ec2 linux console using given Credential (Shared in the email)
4.	Verify the images using  “docker images” command after successful build in Jenkins
5.	Run Docker image as container to access the application
   docker run -d --restart=always -p 80:80 demo

Service1 :

Endpoint url : <host>/projects/<username>
   
Example : http://ec2-13-235-0-181.ap-south-1.compute.amazonaws.com/projects/mdismail1988
Output: [{"id":"204500043","name":"githubprojects","html_url":"https://github.com/mdismail1988/githubprojects"},{"id":"203295920","name":"purchaseInfoService","html_url":"https://github.com/mdismail1988/purchaseInfoService"}]


Service2 :

Endpoint url: <host>/projects/<username>/<project-name> 
   
Example : http://ec2-13-235-0-181.ap-south-1.compute.amazonaws.com/projects/mdismail1988/purchaseInfoService
