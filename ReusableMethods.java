package RestAPITest.TestFramework;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ReusableMethods {
  
	public static XmlPath rawToXml(Response r){ 
		String resData=r.asString();
		//System.out.println(resData);
		XmlPath xData=new XmlPath(resData);
		return xData;
	}
	
	public static JsonPath rawToJson(Response r){ 
		String resData=r.asString();
		//System.out.println(resData);
		JsonPath jData=new JsonPath(resData);
		return jData;
	}
	
	public static String getSessionKey(){  
		//creating session
		RestAssured.baseURI="http://localhost:8080";
	    Response res=given().
	    		header("Content-Type","application/json").
			    body("{ \"username\": \"shubhamkhond21\", \"password\": \"Kruzde361$\" }").
			    when().
			    post("/rest/auth/1/session").
			    then().assertThat().statusCode(200).
			    extract().response();
	            JsonPath jData=ReusableMethods.rawToJson(res);
	            String sessionId=jData.get("session.value");
	            //System.out.println(sessionId);
		        return sessionId;
	      }
	
	public static String getPostedTweetId(String tweet){ 

		String ConsumerKey="GDKlLzPcnFBBr0dAzgH0ZBxQ6";
		String ConsumerSecret="MA9mSEVRRK4xhNTMkzBUoTNFZdVRsFlqdJ3nrjXXeh9eIhGao4";
		String AccessToken="1108611371145613312-W94vTYuLSD42NSNyYcfBZ9byjpHj9a";
		String TokenSecret="OzUdQzfrrJPDhCmweapOdKOduOxM4lc5UH1KwBJZKTm1T";
		String id;
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
		  
		 Response res=given().auth().oauth(ConsumerKey, ConsumerSecret, AccessToken, TokenSecret).
		  queryParam("status",tweet).
		  when().
		  post("/update.json").
		  then().extract().response();
		   String rData=res.asString();
		   JsonPath jData=new JsonPath(rData);
		   String data=jData.get("text").toString();
		   System.out.println("Tweet after creation is:");
		   System.out.println(data);
		 // or you can print directly System.out.println(jData.get("text").toString());
		 //or you can print directly  System.out.println(jData.get("id").toString());
		   id=jData.get("id").toString();
		   System.out.println(id);
		   return id;
	}
	
	public static String GetTweets(){ 
		String ConsumerKey="GDKlLzPcnFBBr0dAzgH0ZBxQ6";
		String ConsumerSecret="MA9mSEVRRK4xhNTMkzBUoTNFZdVRsFlqdJ3nrjXXeh9eIhGao4";
		String AccessToken="1108611371145613312-W94vTYuLSD42NSNyYcfBZ9byjpHj9a";
		String TokenSecret="OzUdQzfrrJPDhCmweapOdKOduOxM4lc5UH1KwBJZKTm1T";
		String id;
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
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
		   return id;
	}
	
      }
