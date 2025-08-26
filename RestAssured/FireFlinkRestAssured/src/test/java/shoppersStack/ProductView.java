package shoppersStack;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ProductView {
    int productId;
	@Test
	public void viewProduct()
	{
		 String product=RestAssured.given().log().all().header("Authorization","Bearer" +Login.token)
		.when().get(Login.url+"/products/alpha")
		.then().assertThat().statusCode(200).log().all().extract().response().asString();
		JsonPath js = new JsonPath(product);
		productId=js.get("productId[0]");
		System.out.println(productId);
	}
}
