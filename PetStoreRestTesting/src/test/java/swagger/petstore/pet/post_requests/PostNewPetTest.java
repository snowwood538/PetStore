package swagger.petstore.pet.post_requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.Pet;
import swagger.petstore.api_requests.Specifications;
import swagger.petstore.models.Category;
import swagger.petstore.models.PetModel;
import swagger.petstore.models.Tag;
import utils.validator.JsonValidator;

import java.util.List;

import static swagger.petstore.api_instances.BaseUrls.BASE_URL;
import static swagger.petstore.api_instances.endpoints.PostEndpoints.POST_NEW_PET;

public class PostNewPetTest {

    @Test
    public static void postNewPetWithRequiredFieldsPositive() {
        PetModel pet = new PetModel();
        pet.setName("Andrew");
        List<String> urls = List.of("photoUrl");
        pet.setPhotoUrls(urls);

        RequestSpecification spec = Specifications.baseRequestSpecification(BASE_URL, POST_NEW_PET);
        Response response = Pet.postPet(spec.contentType("multipart+"), pet);

        Assert.assertEquals(response.getStatusCode(), 200);
        JsonValidator.validatePetObject(response);
    }

    @Test
    public static void postNewPetWithAllFieldsPositive() {
        Category category = new Category();
        category.setId(12349);
        category.setName("Bulldog");

        Tag tag = new Tag();
        tag.setId(41456);
        tag.setName("Newcomer");

        PetModel pet = new PetModel();
        pet.setCategory(category);
        pet.setId(1000);
        pet.setName("Andrew");
        List<String> urls = List.of("photoUrl");
        pet.setPhotoUrls(urls);
        pet.setTags(List.of(tag));
        pet.setStatus("Available");


        RequestSpecification spec = Specifications.baseRequestSpecification(BASE_URL, POST_NEW_PET);
        Response response = Pet.postPet(spec, pet);

        Assert.assertEquals(response.getStatusCode(), 200);
        JsonValidator.validatePetObject(response);
    }
}
