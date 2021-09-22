package swagger.petstore.pet.post.image;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import swagger.petstore.api.requests.PetRequests;
import swagger.petstore.api.requests.Specifications;
import swagger.petstore.models.PetModel;
import utils.factory.pet.factory.PetFactory;

import java.io.File;

import static org.apache.http.HttpStatus.SC_OK;
import static swagger.petstore.api.instances.endpoints.PetEndpoints.PET;
import static swagger.petstore.api.instances.endpoints.PetEndpoints.PET_STRICT;
import static swagger.petstore.api.instances.endpoints.PetEndpoints.UPLOAD_IMAGE;
import static swagger.petstore.api.instances.file_paths.Paths.IMAGE_FILE_PATH;
import static swagger.petstore.api.instances.urls.BaseUrls.BASE_URL;
import static utils.factory.pet.utils.PetTypeVar.FULFILLED_PET;

public class PostNewImagePositiveTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void createNewPetToDelete() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        PetRequests.post(spec, pet);
    }

    @SneakyThrows
    @Test
    public static void postPetImagePositive() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId() + UPLOAD_IMAGE,
                new File(IMAGE_FILE_PATH));

        Response response = PetRequests.post(spec);
        Assert.assertEquals(response.getStatusCode(), SC_OK, "Image doesn't upload to server");
    }
}