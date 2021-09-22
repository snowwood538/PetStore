package swagger.petstore.pet.get.by.id;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import swagger.petstore.api.requests.PetRequests;
import swagger.petstore.api.requests.Specifications;
import swagger.petstore.data_providers.pet.PetDataProvider;
import swagger.petstore.models.PetModel;
import utils.factory.pet.factory.PetFactory;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static swagger.petstore.api.instances.endpoints.PetEndpoints.PET;
import static swagger.petstore.api.instances.urls.BaseUrls.BASE_URL;
import static swagger.petstore.api.instances.endpoints.PetEndpoints.PET_STRICT;
import static utils.factory.pet.utils.PetTypeVar.FULFILLED_PET;

public class GetByIdNegativeTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void createNewPetToGet() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        PetRequests.post(spec, pet);
        RequestSpecification specToDelete = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        PetRequests.delete(specToDelete);
    }

    @Test
    public static void getPetByNotExistingIdNegative() {
        RequestSpecification specToDelete = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        PetRequests.delete(specToDelete);
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        Response response = PetRequests.get(spec);
        Assert.assertEquals(response.getStatusCode(), SC_NOT_FOUND, "Not existing pet exists");
    }

    @Test(dataProvider = "Incorrect_id_for_pets", dataProviderClass = PetDataProvider.class)
    public static void getPetByIncorrectIdNegative(String id) {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + id);
        Response response = PetRequests.get(spec);
        Assert.assertEquals(response.getStatusCode(), SC_NOT_FOUND, "Incompatible type of id works out");
    }
}