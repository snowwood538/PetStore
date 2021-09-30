package swagger.petstore.pet.swagger.put.pet_info;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api.requests.PetRequests;
import swagger.petstore.api.requests.Specifications;
import swagger.petstore.models.swagger.PetModel;
import utils.swagger.factory.PetFactory;
import utils.common.validator.JsonValidator;

import static org.apache.http.HttpStatus.SC_OK;
import static swagger.petstore.api.instances.endpoints.PetEndpoints.PET;
import static swagger.petstore.api.instances.urls.BaseUrls.BASE_URL;
import static utils.common.generator.PetTypeVar.FULFILLED_PET;

public class PutNewPetRequestsInfoPositiveTest {

    @Test
    public static void putNewPetInfoPositive() {
        PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        Response response = PetRequests.put(spec, pet);

        Assert.assertEquals(response.getStatusCode(), SC_OK, "Setting new info has failed");
        JsonValidator.validatePetObject(response);
    }
}