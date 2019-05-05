package RestAPITest.TestFramework;

import org.testng.annotations.Test;

import RestAPITest.TestFramework.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class BasicPostXmlProgram{

	@Test
	public void testPostData() throws IOException {
		String postData=GenerateStringFromResource("C:\\Users\\Shubham\\workspace\\SampleRestAssuredProject\\xmlFolder\\post.xml");
		RestAssured.baseURI="http://216.10.245.166";
		Response res=given().
		queryParam("key","qaclick123").
		body(postData). 
		when().
		post("/maps/api/place/add/xml").
		then().assertThat().statusCode(200).and().contentType(ContentType.XML).
		extract().response();
		
		//String resData=res.asString();
		//System.out.println(resData);
		//XmlPath xData=new XmlPath(resData);
		XmlPath xData=ReusableMethods.rawToXml(res);
	     String placeIdData=xData.get("response.place_id");
	     //response.place_id is the traversing path of xml resData. 
	     System.out.println(placeIdData);
	}
	
	public static String GenerateStringFromResource(String path) throws IOException{ 
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
