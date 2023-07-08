package tek.api.sqa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tek.api.sqa.base.APITestConfig;
import tek.api.utility.EndPoints;

public class GetAccountTest extends APITestConfig{
	
	@Test
	public void getAccountTest () {
		String token = getValidToken();
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token);
		request.queryParam("primaryPersonId", 14);
		Response response = request.when().get(EndPoints.GET_ACCOUNT.getValue());
		response.prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().get("primaryPerson.email"), "automail907@tekschool.us");
		
		
		
	}
	

}
