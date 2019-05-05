package RestAPITest.TestFramework;

import org.testng.annotations.DataProvider;
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



public class JsonSDynamic{

	@Test(dataProvider="sendData")
	public void testPostData(String isbn, String aisle) throws IOException {
		
		RestAssured.baseURI="http://216.10.245.166";
		Response res=given().
		header("Content-Type","application/json").
		body(Payload.addBookDynamic(isbn,aisle)). 
		when().
		post("Library/Addbook.php").
		then().assertThat().statusCode(200).
		extract().response();
		
		JsonPath jData=ReusableMethods.rawToJson(res);
	     String idData=jData.get("ID"); 
	     System.out.println(idData);
	     
	     //now delete the data so that the same data below can be reused again
	     given().
	     body("{"+ 
	   		  "\"ID\" : \""+idData+"\""+
	   		  "}").
	     when().
	     post("/Library/DeleteBook.php").
	     then().assertThat().statusCode(200).and().
	     contentType(ContentType.JSON).and().body("msg",equalTo("book is successfully deleted"));
	}
	
	@DataProvider(name="sendData")
	public Object[][] getData(){ 
		return new Object[][]{ 
			{"SweetuSan","222"},
			{"ChullabhaiAmbe","507"},
			{"Jattubhai","124"}
		};
	}
	
}
