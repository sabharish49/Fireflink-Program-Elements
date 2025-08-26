package gitHub;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

public class CreateRepo {
	@Test
	public void createRepo()
	{
		HashMap body =new HashMap();
		body.put("name", "KGF3");
		body.put("description", "Voilence");
		body.put("private", true);
		
		RestAssured.given().body(body).header("Authorization","Bearer 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")
		.when().post("https://api.github.com/user/repos")
		.then().assertThat().statusCode(201).log().all().body("name", equalTo("KGF30")).body("description", equalTo("Voilence"));
	}
	

}
