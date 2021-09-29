package Demos;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.Test;

public class Demos {

	//i have to provide the base url
	public  static String Baseurl = "https://api.trello.com";
	
	@Test
	public void testcase1() {
		
		//in rest assured 
		RestAssured.baseURI=Baseurl;
		//given is a precondition which will take you
		//parameters//header(Authorization)
		given().
		param("key", "a98a8bb526504d678e4cfcf4287a5a6e").
		param("token", "6b2ce050dbcab6024db4ddbcd04872fdb27e8b540a6b779b581531cb919e148a").
		//when is used to provide method like//get//post//put//delete can also contain the body
		when().
		get("/1/boards/SKVtPsyc").
		then().//post condition response
		assertThat().statusCode(200).contentType(ContentType.JSON).and().
		body("name", equalTo("Arpi")).and().
		body("desc",equalTo("SDET Training on REST"));
		System.out.println("The first method executed successfully");
	}
	
	@Test
	public void testcase2() {
		
		//in rest assured 
		RestAssured.baseURI=Baseurl;
		//given is a precondition which will take you
		//parameters//header(Authorization)
		given().
		param("key", "a98a8bb526504d678e4cfcf4287a5a6e").
		param("token", "6b2ce050dbcab6024db4ddbcd04872fdb27e8b540a6b779b581531cb919e148a").
		//when is used to provide method like//get//post//put//delete can also contain the body
		when().
		get("/1/boards/SKVtPsyc").
		then().//post condition response
		assertThat().statusCode(200).contentType(ContentType.JSON).and().
		header("Expires", equalTo("Thu, 01 Jan 1970 00:00:00")).and().
		header("Content-Encoding", equalTo("gzip"));
		System.out.println("The second method  executed successfully");
	}


}
