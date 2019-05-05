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

public class JiraRestApiUpdateComment{
 
public Properties prop;
  @BeforeTest
  public void beforeTest() throws IOException {
	  prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Shubham\\workspace\\SampleRestAssuredProject\\src\\files\\environment.properties");
		prop.load(fis);
  }
  
  @Test
  public void testJira() {
	  
	  //Adding comment to a particular issue
	  RestAssured.baseURI=prop.getProperty("JIRAHOST");
	  
	 Response res=given().
	  header("Content-Type","application/json").
	  header("Cookie","JSESSIONID="+ReusableMethods.getSessionKey()).
	  pathParam("commentID","10001")
	  .body("{ \r\n\t\"body\":\"Hello, I'm updating this comment for rest api using automation\",\r\n\t\"visibility\": {\r\n    \"type\": \"role\",\r\n    \"value\": \"Administrators\"\r\n  }\r\n}").
	  when().
	  put("/rest/api/2/issue/10003/comment/{commentID}").
	  then().
	  statusCode(200).extract().response();
	  JsonPath jData=ReusableMethods.rawToJson(res);
	  String updatedCommentId=jData.get("id");
	  System.out.println(updatedCommentId);
	  
  }

}