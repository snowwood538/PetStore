package swagger.petstore.pet;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.Specifications;
import swagger.petstore.api_requests.pet.GetRequests;
import swagger.petstore.data_providers.PetDataProvider;
import utils.validator.JsonValidator;

import static swagger.petstore.api_instances.endpoints.PetEndpoints.GET_PETS_BY_ID;

public class GetPetByIdTest {

    @Test
    public static void getPetByIdPositive(int id) {
        RequestSpecification spec = Specifications.baseGetRequestSpecification("https://petstore.swagger.io/v2", GET_PETS_BY_ID + id);
        Response response = GetRequests.getPet(spec);
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonValidator.validatePetObject(response);
    }

    @Test(dataProvider = "Not_existing_id_for_pets", dataProviderClass = PetDataProvider.class)
    public static void getPetByNotExistingIdNegative(int id) {
        RequestSpecification spec = Specifications.baseGetRequestSpecification("https://petstore.swagger.io/v2", GET_PETS_BY_ID + id);
        Response response = GetRequests.getPet(spec);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(dataProvider = "Incorrect_id_for_pets", dataProviderClass = PetDataProvider.class)
    public static void getPetByIncorrectIdNegative(String id) {
        RequestSpecification spec = Specifications.baseGetRequestSpecification("https://petstore.swagger.io/v2", GET_PETS_BY_ID + id);
        Response response = GetRequests.getPet(spec);
        Assert.assertEquals(response.getStatusCode(), 400);
    }
}