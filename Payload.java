package RestAPITest.TestFramework;

public class Payload {
  
	public static String getPostData(){ 
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
		return b;
	}
	
	public static String addBookStatic(){ 
		String payload="{\r\n\r\n\"name\":\"Learn Appium Automation with Java\",\r\n\"isbn\":\"bcdgigi2\",\r\n\"aisle\":\"227\",\r\n\"author\":\"John foe\"\r\n}\r\n";
		return payload;
	}
	
	public static String addBookDynamic(String isbn,String aisle){ 
		String payload="{\r\n\r\n\"name\":\"Learn Appium Automation with Java\",\r\n\"isbn\":\""+isbn+"\",\r\n\"aisle\":\""+aisle+"\",\r\n\"author\":\"John foe\"\r\n}\r\n";
		return payload;
	}
}
