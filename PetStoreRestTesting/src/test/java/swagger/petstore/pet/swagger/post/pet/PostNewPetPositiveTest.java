package swagger.petstore.pet.swagger.post.pet;

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
import static utils.common.generator.PetTypeVar.BASE_PET;
import static utils.common.generator.PetTypeVar.FULFILLED_PET;

public class PostNewPetPositiveTest {

    @Test
    public static void postNewPetWithRequiredFieldsPositive() {
        PetModel pet = PetFactory.createNewPet(BASE_PET);

        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        Response response = PetRequests.post(spec, pet);

        Assert.assertEquals(response.getStatusCode(), SC_OK, "Pet creation failed");
        JsonValidator.validatePetObject(response);
    }

    @Test
    public static void postNewPetWithAllFieldsPositive() {
        PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        Response response = PetRequests.post(spec, pet);

        Assert.assertEquals(response.getStatusCode(), SC_OK, "Pet creation failed");
        JsonValidator.validatePetObject(response);
    }
}