package com.api.petstore.pet.swagger.delete;

import com.api.petstore.models.pet.PetModel;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.api.petstore.swagger.requests.PetRequests;
import com.api.petstore.swagger.requests.Specifications;
import utils.common.factory.pet.PetFactory;

import static org.apache.http.HttpStatus.SC_OK;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET_STRICT;
import static com.api.petstore.swagger.instances.urls.BaseUrls.BASE_URL;
import static utils.common.factory.enums.PetTypeVar.FULFILLED_PET;

public class DeletePositiveTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void beforeDeletePetByIDTests() {
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