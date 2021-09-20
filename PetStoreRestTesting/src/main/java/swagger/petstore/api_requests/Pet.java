package swagger.petstore.api_requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Pet {

    public static Response getPet(RequestSpecification spec) {
        return RestAssured.given()
                .spec(spec)
                .when()
                .get()
                .then()
                .log().all()
                .extract().response();
    }
}
