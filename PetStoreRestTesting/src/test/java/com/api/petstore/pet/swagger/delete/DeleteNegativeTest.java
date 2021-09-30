package com.api.petstore.pet.swagger.delete;

import com.api.petstore.models.pet.PetModel;
import com.api.petstore.swagger.requests.PetRequests;
import com.api.petstore.swagger.requests.Specifications;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.common.factory.pet.PetFactory;

import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET_STRICT;
import static com.api.petstore.swagger.instances.urls.BaseUrls.BASE_URL;
import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static utils.common.factory.enums.PetTypeVar.FULFILLED_PET;

public class DeleteNegativeTest {
    private static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void beforeDeleteNegativeTests() {
        RequestSpecification specToCreate = Specifications.requestSpecification(BASE_URL, PET);
        PetRequests.post(specToCreate, pet);
        RequestSpecification specToDelete = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        PetRequests.delete(specToDelete);
    }

    @Test
    public static void deleteNotExistingPetByIdNegativeTest() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        Response response = PetRequests.delete(spec);
        Assert.assertEquals(response.getStatusCode(), SC_NOT_FOUND, "Delete not existing pet has done successfully");
    }

    @Test
    public static void sendDeleteRequestWithEmptyQueryTest() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT);
        Response response = PetRequests.delete(spec);
        Assert.assertEquals(response.getStatusCode(), SC_METHOD_NOT_ALLOWED, "Empty query doesn't invoke an error");
    }

    @Test
    public static void sendDeleteRequestWithIncorrectDataTypeQueryTest() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT);
        Response response = PetRequests.delete(spec);
        Assert.assertEquals(response.getStatusCode(), SC_METHOD_NOT_ALLOWED, "Incorrect datatype doesn't invoke an error");
    }
}