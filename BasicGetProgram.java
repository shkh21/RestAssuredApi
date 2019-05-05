package RestAPITest.TestFramework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;



public class BasicGetProgram{
    
	@Test
	public void test() {
	  RestAssured.baseURI="https://maps.googleapis.com";
	  
	  given().
	  param("location","-33.8670522,151.1957362").
	  param("radius","1500").log().all(). //u can write log().all() any param,it will still work
	  param("key","AIzaSyA13tBVgRrNeY2SOE4CDIGaAusAy3Yewvc").
	  when().
	  get("maps/api/place/nearbysearch/json").
	  then().
	  assertThat().statusCode(200).and().
	  contentType(ContentType.JSON).and().
	  body("results[0].name",equalTo("Sydney")).and().
	  body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
	  header("Server","scaffolding on HTTPServer2");
      
	  /*
	   In this basic program we are verfying:-
	   Status code of the response,
	   Content Type,
	   Header response
	  */ 
	}

}
