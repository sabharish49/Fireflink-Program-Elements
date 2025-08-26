package shoppersStack;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class Login {
	static String url="https://www.shoppersstack.com/shopping";
	int shopperId;
	static String token;
	int productId;
	@Test(priority=1)
	public void login()
	{
		HashMap lb = new HashMap();
		lb.put("email", "nayanahn2000march@gmail.com");
		lb.put("password", "Nayana@1025");
		lb.put("role", "SHOPPER");
		String login = RestAssured.given().contentType(ContentType.JSON).body(lb).log().all()
		.when().post(url+"/users/login")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(login);
		shopperId=js.get("data.userId");
		token=js.get("data.jwtToken");
		System.out.println(shopperId);
		System.out.println(token);
	}
	@Test()
	public void viewProduct()
	{
	 String product=RestAssured.given().log().all()
				.when().get(Login.url+"/products/alpha")
				.then().assertThat().statusCode(200).log().all().extract().response().asString();
				JsonPath js = new JsonPath(product);
				productId = js.get("searchTags");
				System.out.println(productId);
}
}
