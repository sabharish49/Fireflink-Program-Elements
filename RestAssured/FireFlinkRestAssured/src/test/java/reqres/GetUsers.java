package reqres;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetUsers {
	@Test
	public void ListUsers()
	{
		RestAssured.given().when().get("https://reqres.in/api/users?page=2")
				
		.then().assertThat().statusCode(200).log().all();
		
	}

}
