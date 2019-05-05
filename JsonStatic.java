package RestAPITest.TestFramework;

import org.testng.annotations.Test;

import RestAPITest.TestFramework.Payload;
import RestAPITest.TestFramework.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class JsonStatic{

	@Test
	public void testPostData() throws IOException {
		
		RestAssured.baseURI="http://216.10.245.166";
		Response res=given().
		header("Content-Type","application/json").
		body(Payload.addBookStatic()). 
		when().
		post("Library/Addbook.php").
		then().assertThat().statusCode(200).
		extract().response();
		
		JsonPath jData=ReusableMethods.rawToJson(res);
	     String idData=jData.get("ID"); 
	     System.out.println(idData);
	}
	
}
