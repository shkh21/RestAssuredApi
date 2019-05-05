package RestAPITest.TestFramework;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class AddDeleteProgram{
	@Test
	public void test() {
		String b="{" +

				"\"location\":{" +

				"\"lat\" : -38.383494," +

				"\"lng\" : 33.427362" +

				"}," +

				"\"accuracy\":50," +

				"\"name\":\"Frontline house\"," +

				"\"phone_number\":\"(+91) 983 893 3937\"," +

				"\"address\" : \"29, side layout, cohen 09\"," +

				"\"types\": [\"shoe park\",\"shop\"]," +

				"\"website\" : \"http://google.com\"," +

				"\"language\" : \"French-IN\"" +

				"}";
		//Task 1:- Grab the response
		RestAssured.baseURI = "http://216.10.245.166";
	
	  Response res=given().
		queryParam("key", "qaclick123").
		body(b).
		when().
		post("/maps/api/place/add/json").
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
	  queryParam("key","qaclick123").
	  body("{"+ 
		  "\"place_id\" : \""+placeid+"\""+
	  "}").
	  when().
	  post("/maps/api/place/delete/json").
	  then().assertThat().statusCode(200).and()
		.contentType(ContentType.JSON).and().
		body("status", equalTo("OK"));
	}
}
