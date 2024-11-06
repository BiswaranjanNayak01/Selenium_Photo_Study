package restAssuredJava;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class DeleteRequest {
    public static void main(String[] args) {
        deleteRequest();
    }
    public static void deleteRequest(){
        RestAssured.baseURI="";
        RequestSpecification requestSpecification=given()
                .header("","")
                .body("")
                .when();
        Response response=requestSpecification.post();
        response.then().assertThat().statusCode(200).log().all();
        response.getBody().jsonPath().get("");
    }
}
