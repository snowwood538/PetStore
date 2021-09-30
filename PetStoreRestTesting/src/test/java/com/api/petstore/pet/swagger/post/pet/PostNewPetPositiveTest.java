package com.api.petstore.pet.swagger.post.pet;

import com.api.petstore.models.pet.PetModel;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.api.petstore.swagger.requests.PetRequests;
import com.api.petstore.swagger.requests.Specifications;
import utils.common.factory.pet.PetFactory;
import utils.common.validator.JsonValidator;

import static org.apache.http.HttpStatus.SC_OK;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET;
import static com.api.petstore.swagger.instances.urls.BaseUrls.BASE_URL;
import static utils.common.factory.enums.PetTypeVar.BASE_PET;
import static utils.common.factory.enums.PetTypeVar.FULFILLED_PET;

public class PostNewPetPositiveTest {

    @Test
    public static void postNewPetWithRequiredFieldsPositive() {
        PetModel pet = PetFactory.createNewPet(BASE_PET);

        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        Response response = PetRequests.post(spec, pet);

        Assert.assertEquals(response.getStatusCode(), SC_OK, "Pet creation failed");
        JsonValidator.validateObject(response);
    }

    @Test
    public static void postNewPetWithAllFieldsPositive() {
        PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        Response response = PetRequests.post(spec, pet);

        Assert.assertEquals(response.getStatusCode(), SC_OK, "Pet creation failed");
        JsonValidator.validateObject(response);
    }
}