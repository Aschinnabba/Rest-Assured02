package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.UserPojo;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTest {

	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostUser(String UserID,String UserName ,String FirstName,String LastName,String useremail,String Password,String Phone) {

		UserPojo userPayload =new UserPojo();
		userPayload.setId(Integer.parseInt(UserID));
		userPayload.setUsername(UserName);
		userPayload.setFirstName(FirstName);
		userPayload.setLastName(LastName);
		userPayload.setEmail(useremail);
		userPayload.setPassword(Password);
		userPayload.setPhone(Phone);

		Response response= UserEndpoints.creatUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void deletUser(String Username) {
		Response response=	UserEndpoints.deleteUser(Username);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);

	}
}
