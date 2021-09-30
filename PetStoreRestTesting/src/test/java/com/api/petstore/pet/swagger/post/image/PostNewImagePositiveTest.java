package com.api.petstore.pet.swagger.post.image;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.api.petstore.swagger.requests.PetRequests;
import com.api.petstore.swagger.requests.Specifications;
import com.api.petstore.models.pet.PetModel;
import utils.common.factory.pet.PetFactory;

import java.io.File;

import static org.apache.http.HttpStatus.SC_OK;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET_STRICT;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.UPLOAD_IMAGE;
import static com.api.petstore.swagger.instances.file_paths.Paths.IMAGE_FILE_PATH;
import static com.api.petstore.swagger.instances.urls.BaseUrls.BASE_URL;
import static utils.common.factory.enums.PetTypeVar.FULFILLED_PET;

public class PostNewImagePositiveTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void beforeClassPostNewImageTests() {
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