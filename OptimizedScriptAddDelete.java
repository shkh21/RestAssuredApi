package RestAPITest.TestFramework;

import org.testng.annotations.Test;

import RestAPITest.TestFramework.Payload;
import RestAPITest.TestFramework.Resource;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeClass;

public class OptimizedScriptAddDelete{
	public Properties prop;
  @BeforeClass
  public void beforeClass() throws IOException {
	  prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Shubham\\workspace\\SampleRestAssuredProject\\src\\files\\environment.properties");
		prop.load(fis);
		//prop.get("HOST");
  }
  
  @Test
	public void test() {
		
		//Task 1:- Grab the response
		RestAssured.baseURI = prop.getProperty("HOST");
	
	  Response res=given().
		queryParam("key",prop.getProperty("KEY")).
		body(Payload.getPostData()).
		when().
		post(Resource.placePostData()).
		then().assertThat().statusCode(200).
		and().contentType(ContentType.JSON).and().
		body("status", equalTo("OK")).
		extract().response();
	  
	//Task 2: Grab the placeid from response
	  String responseString=res.asString();
	  System.out.println(responseString);
	  JsonPath js=new JsonPath(responseString);
	  String placeid= js.get("place_id");
	  System.out.println(placeid);
	  
	  //Place this placeid in the delete request
	  given().
	  queryParam("key",prop.getProperty("KEY")).
	  body("{"+ 
		  "\"place_id\" : \""+placeid+"\""+
	  "}").
	  when().
	  post(Resource.placeDeleteData()).
	  then().assertThat().statusCode(200).and()
		.contentType(ContentType.JSON).and().
		body("status", equalTo("OK"));
	}
}
