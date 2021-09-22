package swagger.petstore.api.requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import swagger.petstore.models.PetModel;

public class PetRequests {

    public static Response get(RequestSpecification spec) {
        return RestAssured.given()
                .spec(spec)
                .when()
                .get()
                .then()
                .log().all()
                .extract().response();
    }

    public static Response post(RequestSpecification spec, PetModel pet) {
        return RestAssured.given()
                .spec(spec)
                .body(pet)
                .when()
                .post()
                .then()
                .log().all()
                .extract().response();
    }

    public static Response post(RequestSpecification spec) {
        return RestAssured.given()
                .spec(spec)
                .when()
                .post()
                .then()
                .log().all()
                .extract().response();
    }

    public static Response put(RequestSpecification spec, PetModel pet) {
        return RestAssured.given()
                .spec(spec)
                .body(pet)
                .when()
                .put()
                .then()
                .log().all()
                .extract().response();
    }

    public static Response delete(RequestSpecification spec) {
        return RestAssured.given()
                .spec(spec)
                .when()
                .delete()
                .then()
                .log().all()
                .extract().response();
    }
}