package swagger.petstore.pet.delete;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import swagger.petstore.api.requests.PetRequests;
import swagger.petstore.api.requests.Specifications;
import swagger.petstore.models.PetModel;
import utils.factory.pet.factory.PetFactory;

import static org.apache.http.HttpStatus.SC_OK;
import static swagger.petstore.api.instances.endpoints.PetEndpoints.PET;
import static swagger.petstore.api.instances.endpoints.PetEndpoints.PET_STRICT;
import static swagger.petstore.api.instances.urls.BaseUrls.BASE_URL;
import static utils.factory.pet.utils.PetTypeVar.FULFILLED_PET;

public class DeletePositiveTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void createNewPetToDelete() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        PetRequests.post(spec, pet);
    }

    @Test
    public static void deletePetByIdPositiveTest() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        Response response = PetRequests.delete(spec);
        Assert.assertEquals(response.getStatusCode(), SC_OK, "Couldn't delete existing pet by id");
    }
}