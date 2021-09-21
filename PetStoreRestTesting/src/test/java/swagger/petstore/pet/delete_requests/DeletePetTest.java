package swagger.petstore.pet.delete_requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.Pet;
import swagger.petstore.api_requests.Specifications;

import static swagger.petstore.api_instances.BaseUrls.BASE_URL;
import static swagger.petstore.api_instances.endpoints.PetEndpoints.PET_STRICT;

public class DeletePetTest {

    @Test
    public static void deletePetByIdTest() {
        RequestSpecification spec = Specifications.baseRequestSpecification(BASE_URL, PET_STRICT + 10);
        Response response = Pet.deletePet(spec);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public static void deleteNotExistingPetByIdNegativeTest() {
        RequestSpecification spec = Specifications.baseRequestSpecification(BASE_URL, PET_STRICT + 10);
        Response response = Pet.deletePet(spec);
        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
