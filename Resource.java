package RestAPITest.TestFramework;

public class Resource {
  
	public static String placePostData(){ 
		String resource="/maps/api/place/add/json";
		return resource;
	}
	
	public static String placeDeleteData(){ 
		String resource="/maps/api/place/delete/json";
		return resource;
	}
}
