package swagger.petstore.pet;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.Specifications;
import swagger.petstore.api_requests.pet.GetRequests;
import utils.validator.JsonValidator;
import utils.logger.Log;

import static swagger.petstore.api_instances.endpoints.PetEndpoints.GET_PETS_BY_ID;

public class GetPetByIdTest {

    @Test
    public static void getPetByIdPositive() {
        RequestSpecification spec = Specifications.baseGetRequestSpecification("https://petstore.swagger.io/v2", GET_PETS_BY_ID + 1);
        Response response = GetRequests.getPet(spec);
        Assert.assertEquals(response.getStatusCode(), 200);
        Log.warn("Test");
        JsonValidator.validatePetObject(response);
    }
}