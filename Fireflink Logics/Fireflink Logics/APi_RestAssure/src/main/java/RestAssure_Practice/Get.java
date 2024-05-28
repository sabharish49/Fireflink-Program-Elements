package RestAssure_Practice;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class Get {
	
	@Test
	public void list() {
		//given
		//when
		//then
	
		RestAssured.given().log().all()
		.when().get("https://reqres.in/api/users?page=2")
		.then().assertThat().statusCode(200).log().all();
	}
}
