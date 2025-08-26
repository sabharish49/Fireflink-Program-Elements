package reqres;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUser {
	public class Users {
		HashMap data;
		@Test
		public void createUser()
		{
		    data= new HashMap();
			data.put("name", "morpheus");
			data.put("job", "leader");
			RestAssured.given().body(data)
		    .when().post("https://reqres.in/api/users")
	    	.then().assertThat().statusCode(201).log().all();	
		}
		@Test
		public void getData()
		{
			RestAssured.given().contentType(ContentType.JSON).body(data)
			.when().get().then().assertThat().statusCode(200).log().all().equals("OK");
		}
//		public void getMap()
//		{
//			RestAssured.given().contentType(ContentType.JSON).body(data)
//			.when().get().then().statusCode(200).log().all();
//		}
	}
}
