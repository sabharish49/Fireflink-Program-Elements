package Shopper_stack;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class Get {
	
	
	@Test
	public static void get() {
		
		HashMap data= new HashMap();
		data.put("email", "gouthamsam098@gmail.com");
		data.put("password", "Abhisam$098");
		data.put("role", "SHOPPER");
		
		RestAssured.given().contentType(ContentType.JSON).body(data).log().all()
		.when().post("https://www.shoppersstack.com/users/login")
		.then().assertThat().statusCode(200);
	}

}
