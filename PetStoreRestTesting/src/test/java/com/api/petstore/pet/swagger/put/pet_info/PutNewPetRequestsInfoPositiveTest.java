package com.api.petstore.pet.swagger.put.pet_info;

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
import static utils.common.factory.enums.PetTypeVar.FULFILLED_PET;

public class PutNewPetRequestsInfoPositiveTest {

    @Test
    public static void putNewPetInfoPositive() {
        PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        Response response = PetRequests.put(spec, pet);

        Assert.assertEquals(response.getStatusCode(), SC_OK, "Setting new info has failed");
        JsonValidator.validateObject(response);
    }
}