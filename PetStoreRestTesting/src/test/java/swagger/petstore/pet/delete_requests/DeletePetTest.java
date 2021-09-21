package swagger.petstore.pet.delete_requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.Pet;
import swagger.petstore.api_requests.Specifications;

import static swagger.petstore.api_instances.BaseUrls.BASE_URL;
import static swagger.petstore.api_instances.endpoints.DeleteEndpoints.DELETE_PET_BY_ID;

public class DeletePetTest {

    @Test
    public static void deletePetByIdTest() {
        RequestSpecification spec = Specifications.baseRequestSpecification(BASE_URL, DELETE_PET_BY_ID + 10);
        Response response = Pet.deletePet(spec);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public static void deleteNotExistingPetByIdTest() {
        RequestSpecification spec = Specifications.baseRequestSpecification(BASE_URL, DELETE_PET_BY_ID + 10);
        Response response = Pet.deletePet(spec);
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
