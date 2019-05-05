package RestAPITest.TestFramework;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class TwitterApi2{
	
	String ConsumerKey="GDKlLzPcnFBBr0dAzgH0ZBxQ6";
	String ConsumerSecret="MA9mSEVRRK4xhNTMkzBUoTNFZdVRsFlqdJ3nrjXXeh9eIhGao4";
	String AccessToken="1108611371145613312-W94vTYuLSD42NSNyYcfBZ9byjpHj9a";
	String TokenSecret="OzUdQzfrrJPDhCmweapOdKOduOxM4lc5UH1KwBJZKTm1T";
	String id;
  @Test
  public void RetriveTweet() {
	  RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
	  
	 Response res=given().auth().oauth(ConsumerKey, ConsumerSecret, AccessToken, TokenSecret).
	  queryParam("count","2").
	  when().
	  get("/home_timeline.json").
	  then().extract().response();
	   String rData=res.asString();
	   JsonPath jData=new JsonPath(rData);
	   String data=jData.get("text").toString();
	   System.out.println(data);
	 // or you can print directly use System.out.println(jData.get("text").toString());
	  // or you can print directly use System.out.println(jData.get("id").toString());
	   id=jData.get("id").toString();
	   System.out.println(id);
	   
  }

  @Test
  public void PostTweet(){ 
	  RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
	  
		 Response res=given().auth().oauth(ConsumerKey, ConsumerSecret, AccessToken, TokenSecret).
		  queryParam("status","Creating this tweet using automation").
		  when().
		  post("/update.json").
		  then().extract().response();
		   String rData=res.asString();
		   JsonPath jData=new JsonPath(rData);
		   String data=jData.get("text").toString();
		   System.out.println(data);
		 // or you can print directly System.out.println(jData.get("text").toString());
		 //or you can print directly  System.out.println(jData.get("id").toString());
		   id=jData.get("id").toString();
		   System.out.println(id);
  }
  
  @Test
  public void DeleteTweet(){
	  PostTweet();
	  RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
		 Response res=given().auth().oauth(ConsumerKey, ConsumerSecret, AccessToken, TokenSecret).
		  when().
		  post("/destroy/"+id+".json").
		  then().extract().response();
		   String rData=res.asString();
		   JsonPath jData=new JsonPath(rData);
		   String data=jData.get("text").toString();
		   System.out.println(data);
		 // or you can print directly using System.out.println(jData.get("text").toString());
		  //or you can print directly using System.out.println(jData.get("id").toString());
		   id=jData.get("id").toString();
		   System.out.println(id);
		   System.out.println(jData.get("truncated").toString());
  }
}
