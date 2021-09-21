package swagger.petstore.pet.put_requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.Pet;
import swagger.petstore.api_requests.Specifications;
import swagger.petstore.models.Category;
import swagger.petstore.models.PetModel;
import utils.validator.JsonValidator;

import java.util.List;

import static swagger.petstore.api_instances.BaseUrls.BASE_URL;
import static swagger.petstore.api_instances.endpoints.PutEndpoints.PUT_NEW_PET_INFO;

public class PutPetNewInfoTest {

    @Test
    public static void putNewPetInfoPositive() {
        Category category = new Category();
        category.setId(12349);
        category.setName("Bulldog");

        PetModel pet = new PetModel();
        pet.setCategory(category);
        pet.setId(1000);
        pet.setName("Anqwedrew");
        List<String> urls = List.of("photoUrl");
        pet.setPhotoUrls(urls);
        pet.setStatus("Available");


        RequestSpecification spec = Specifications.baseRequestSpecification(BASE_URL, PUT_NEW_PET_INFO);
        Response response = Pet.putPet(spec, pet);

        Assert.assertEquals(response.getStatusCode(), 200);
        JsonValidator.validatePetObject(response);
    }
}
