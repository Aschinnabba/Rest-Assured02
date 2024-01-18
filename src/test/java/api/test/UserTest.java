package api.test;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.UserPojo;
import io.restassured.response.Response;

public class UserTest {
	Faker faker;
	UserPojo userPayload;
	public Logger logger;
	@BeforeClass
	public void setup() {
		faker =new Faker();
		userPayload=new UserPojo();
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		logger=LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void postUser() {
		logger.info("********User creating ************");
		Response response= UserEndpoints.creatUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("********User created ************");
	}
	@Test(priority=2)
	public void getUser() {
		logger.info("******** Get  User  ************");
		Response response=	UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority=3)
	public void UpdateUser() {
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());	
		logger.info("******** Updating User ************");

		Response response= UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);

		//checking data after Update
		Response responseAfterupdate= UserEndpoints.readUser(this.userPayload.getUsername());
		response.then().log().body();
		Assert.assertEquals(responseAfterupdate.getStatusCode(),200);
		logger.info("********User Updated************");

	}
	@Test(priority=4)
	public void deleteUser() {
		logger.info("******** delete User************");

		Response response=	UserEndpoints.deleteUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("********User Deleted************");

	}
}
