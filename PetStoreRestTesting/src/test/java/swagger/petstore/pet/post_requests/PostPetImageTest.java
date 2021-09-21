package swagger.petstore.pet.post_requests;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.Pet;
import swagger.petstore.api_requests.Specifications;

import java.io.File;

import static swagger.petstore.api_instances.BaseUrls.BASE_URL;
import static swagger.petstore.api_instances.endpoints.PostEndpoints.POST_NEW_PET;
import static swagger.petstore.api_instances.endpoints.PostEndpoints.POST_PET_IMAGE;

public class PostPetImageTest {

    @SneakyThrows
    @Test
    public static void postPetImagePositive() {
        RequestSpecification spec = Specifications.postImageRequestSpecification(BASE_URL, POST_NEW_PET + "/" + 100 + POST_PET_IMAGE);
        Response response = Pet.postPetImage(spec, new File("C:/Projects/RestPracticeWork/petstore/PetStoreRestTesting/src/main/resources/images/petImage.jpg"));

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
