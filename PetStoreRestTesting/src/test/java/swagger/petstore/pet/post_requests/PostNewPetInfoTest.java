package swagger.petstore.pet.post_requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.Pet;
import swagger.petstore.api_requests.Specifications;
import swagger.petstore.models.PetModel;

import static swagger.petstore.api_instances.BaseUrls.BASE_URL;
import static swagger.petstore.api_instances.endpoints.PostEndpoints.POST_NEW_PET_INFO;

public class PostNewPetInfoTest {

    @Test
    public static void postNewPetInfoPositive() {
        PetModel pet = new PetModel();
        pet.setName("Andrew");
        pet.setStatus("Available");

        RequestSpecification spec = Specifications.postNewPetInfoSpecification(BASE_URL, POST_NEW_PET_INFO + 10);
        Response response = Pet.postPet(spec, pet);

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
