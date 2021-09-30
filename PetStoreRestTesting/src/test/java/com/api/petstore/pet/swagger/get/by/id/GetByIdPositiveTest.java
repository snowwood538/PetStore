package com.api.petstore.pet.swagger.get.by.id;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.api.petstore.swagger.requests.PetRequests;
import com.api.petstore.swagger.requests.Specifications;
import com.api.petstore.models.pet.PetModel;
import utils.common.factory.pet.PetFactory;
import utils.common.validator.JsonValidator;

import static org.apache.http.HttpStatus.SC_OK;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET_STRICT;
import static com.api.petstore.swagger.instances.urls.BaseUrls.BASE_URL;
import static utils.common.factory.enums.PetTypeVar.FULFILLED_PET;

public class GetByIdPositiveTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void beforeGetPetById() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        PetRequests.post(spec, pet);
    }

    @Test
    public static void getPetByIdPositive() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        Response response = PetRequests.get(spec);
        Assert.assertEquals(response.getStatusCode(), SC_OK, "Couldn't get pet by id");
        JsonValidator.validateObject(response);
    }
}