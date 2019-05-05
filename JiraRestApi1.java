package RestAPITest.TestFramework;

import org.testng.annotations.Test;

import RestAPITest.TestFramework.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;

public class JiraRestApi1{
 
public Properties prop;
  @BeforeTest
  public void beforeTest() throws IOException {
	  prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Shubham\\workspace\\SampleRestAssuredProject\\src\\files\\environment.properties");
		prop.load(fis);
  }
  
  @Test
  public void testJira() {
	  
	  //Creating Issue/Defect
	  RestAssured.baseURI=prop.getProperty("JIRAHOST");
	  
	 Response res=given().
	  header("Content-Type","application/json").
	  header("Cookie","JSESSIONID="+ReusableMethods.getSessionKey()).
	  body("{\r\n\t\"fields\":{ \r\n\t\t\"project\":{ \r\n\t\t\t\"key\":\"RES\"\r\n\t\t},\r\n\t\t\"summary\":\"Issue Automated\",\r\n\t\t\"description\":\"Creating my third bug through automation\",\r\n\t\t\"issuetype\":{ \r\n\t\t\t\"name\":\"Bug\"\r\n\t\t}\r\n\t}\r\n}").
	  when().
	  post("/rest/api/2/issue").
	  then().
	  statusCode(201).extract().response();
	  JsonPath jData=ReusableMethods.rawToJson(res);
	  String issueId=jData.get("id");
	  System.out.println(issueId);
	  
  }

}
