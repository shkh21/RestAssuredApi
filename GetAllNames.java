package RestAPITest.TestFramework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import RestAPITest.TestFramework.ReusableMethods;



public class GetAllNames{
    
	@Test
	public void test() {
	  RestAssured.baseURI="https://maps.googleapis.com";
	  
	  Response res=given().
	  param("location","-33.8670522,151.1957362").
	  param("radius","1500").
	  param("key","AIzaSyA13tBVgRrNeY2SOE4CDIGaAusAy3Yewvc").log().all().
	  when().
	  get("maps/api/place/nearbysearch/json").
	  then().
	  assertThat().statusCode(200).and().
	  contentType(ContentType.JSON).and().
	  body("results[0].name",equalTo("Sydney")).and().
	  body("results[0].place_id",equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
	  header("Server","scaffolding on HTTPServer2").log().body().
	  //now to grab name of every index of results array
	  extract().response();
	  JsonPath jData=ReusableMethods.rawToJson(res);
	  int count=jData.get("results.size()");
	  System.out.println(count);
	  for(int i=0;i<count;i++){ 
		 String nameList=jData.get("results["+i+"].name");
		 System.out.println("Names in array are:" +nameList);
	  }
	}

}
