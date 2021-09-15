package swagger.petstore.api_requests.pet;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static swagger.petstore.api_instances.BaseUrls.BASE_URL;
import static swagger.petstore.api_instances.endpoints.PetEndpoints.*;
import static swagger.petstore.api_instances.queries.PetQueries.petStatus;

public class GetRequests {

    private GetRequests () {

    }

    public static Response getPetsByStatus(String[] status) {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .basePath(GET_PETS_BY_STATUS)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam(petStatus, status[0])
                .log().all()
                .when()
                .get()
                .then()
                .log().all()
                .extract()
                .response();
    }

    public static Response getPetById(int id) {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .basePath(GET_PETS_BY_ID + id)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get()
                .then()
                .log().body()
                .extract().response();
    }
}
