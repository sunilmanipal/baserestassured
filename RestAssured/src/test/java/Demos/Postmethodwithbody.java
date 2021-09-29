package Demos;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class Postmethodwithbody {
	public String acessToken;
	public String id;
	@Test(enabled=false)
	public void signup()
	{
		//first step
		RestAssured.baseURI ="https://ecommerceservice.herokuapp.com";
		
		String requestbody = "{\r\n" + 
				"	\"email\": \"suniltrng3@gmail.com\",\r\n" + 
				"	\"password\": \"suniltrng3@123\"\r\n" + 
				"}\r\n" + 
				"";
		
		Response response = given()
		.header("content-Type","application/json")
		.body(requestbody)
		
		.when()
		.post("/user/signup")
		
		.then()
		.assertThat().statusCode(201).and()
		.contentType(ContentType.JSON)
		.extract().response();
		System.out.println(response.asString());
		
	}
	
	
	@Test(priority=0)
	public void Login(){
RestAssured.baseURI ="https://ecommerceservice.herokuapp.com";
		
		String requestbody = "{\r\n" + 
				"	\"email\": \"suniltrng3@gmail.com\",\r\n" + 
				"	\"password\": \"suniltrng3@123\"\r\n" + 
				"}\r\n" + 
				"";
		
		Response response = given()
		.header("content-Type","application/json")
		.body(requestbody)
		
		.when()
		.post("/user/login")
		
		.then()
		.assertThat().statusCode(200).and()
		.contentType(ContentType.JSON)
		.extract().response();
		//i want to convert the response from string to json
		String jsonresp = response.asString();
		JsonPath responsebody = new JsonPath(jsonresp);
		acessToken = responsebody.get("accessToken");
		System.out.println("The AccessToken is" +acessToken);
	}
	
	@Test(priority=1)
	public void getallusers()
	{
		RestAssured.baseURI ="https://ecommerceservice.herokuapp.com";
		
		Response response = given()
				.header("content-Type","application/json")
				.header("Authorization","bearer " + acessToken)
				
				.when()
				.get("/user")
				.then()
				.assertThat().statusCode(200).and()
				.contentType(ContentType.JSON)
				.extract().response();
		String jsonresp = response.asString();
		JsonPath responsebody = new JsonPath(jsonresp);
		id = responsebody.get("users[2]._id");
		System.out.println("The user is "+ id);
	}
	
	@Test(priority=2)
	public void delete()
	{
RestAssured.baseURI ="https://ecommerceservice.herokuapp.com";
		
		Response response = given()
				.header("content-Type","application/json")
				.header("Authorization","bearer " + acessToken)
				
				.when()
				.delete("/user/"+id)
				.then()
				.assertThat().statusCode(200).and()
				.contentType(ContentType.JSON)
				.extract().response();
		String jsonresp = response.asString();
		JsonPath responsebody = new JsonPath(jsonresp);
	String 	message = responsebody.get("message");
	System.out.println(message);
		
	}

}
