package com.api.petstore.pet.swagger.post.image;

import com.api.petstore.models.pet.PetModel;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.api.petstore.swagger.requests.PetRequests;
import com.api.petstore.swagger.requests.Specifications;
import com.api.petstore.data_providers.PetDataProvider;
import utils.common.factory.pet.PetFactory;

import java.io.File;

import static org.apache.http.HttpStatus.SC_NOT_ACCEPTABLE;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET_STRICT;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.UPLOAD_IMAGE;
import static com.api.petstore.swagger.instances.urls.BaseUrls.BASE_URL;
import static utils.common.factory.enums.PetTypeVar.FULFILLED_PET;

public class PostNewImageNegativeTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void beforeClassPostNewImageTests() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        PetRequests.post(spec, pet);
    }

    @SneakyThrows
    @Test(dataProvider = "Path_to_files_to_add_images", dataProviderClass = PetDataProvider.class)
    public static void postPetImageNegative(String path) {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId() + UPLOAD_IMAGE,
                new File(path));

        Response response = PetRequests.post(spec);
        Assert.assertEquals(response.getStatusCode(), SC_NOT_ACCEPTABLE, "Incompatible type of file has been uploaded");
    }
}