package tek.api.sqa.tests;

import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.api.sqa.base.APITestConfig;
import tek.api.utility.EndPoints;

public class SecurityTest2 extends APITestConfig {

	@Test
	public void testGenerateTokenValidUser() {
		// First Step to set Base URL done at BeforeMethod Annotation
		// 2) Prepare Request.
		// Create Request Body.
		// Option 1) Creating a Map
		// Option 2) Creating an Encapsulated Object.
		// Option 3) String as JSON Object. (Not Recommended)
		Map<String, String> tokenRequestBody = new HashMap<>();
		tokenRequestBody.put("username", "supervisor");
		tokenRequestBody.put("password", "tek_supervisor");
		
		RequestSpecification request = RestAssured.given().body(tokenRequestBody);
		request.contentType(ContentType.JSON);
		Response response = request.when().post(EndPoints.TOKEN_GENERATION.getValue());
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
		String generatedToken = response.jsonPath().get("token");
		Assert.assertNotNull(generatedToken);
		
		
	}
	@Test (dataProvider = "invalidCredentials")
	public void testGenerateTokenWithInvalidCredintials (String username, String password, String errorMessage) {
		Map<String, String> requestBody = new HashMap<>();
		requestBody.put("username", username);
		requestBody.put("password", password);
		RequestSpecification request = RestAssured.given().body(requestBody);
		request.contentType(ContentType.JSON);
		Response response =  request.when().post(EndPoints.TOKEN_GENERATION.getValue());
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 400);
		Assert.assertEquals(response.jsonPath().get("errorMessage"), errorMessage);
		
		
	}
	@DataProvider(name = "invalidCredentials")
	private Object[][] invalidCredentials () {
		Object[][] data = {
				{"invalidUsername", "tek_supervisor", "User not found"},
				{"supervisor", "invalidPassword", "Password Not Matched" }
		};
		return data;
	}

}
