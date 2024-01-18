package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	public static Response creatUser(UserPojo payload) {
		Response response=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(Roots.post_url);
		return response;
	}
	public static Response readUser(String userName) {
		Response response=given()
				.urlEncodingEnabled(true)
				.pathParam("username",userName)
				.when()
				.get(Roots.get_url);
		return response;
	}
	public static Response updateUser(String userName,UserPojo payload) {
		Response response=given()
				.pathParams("username", userName)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.put(Roots.update_url);
		return response;
	}
	public static Response deleteUser(String userName) {
		Response response=given()
				.pathParam("username",userName)
				.when()
				.delete(Roots.delete_url);
		return response;
	}



}
