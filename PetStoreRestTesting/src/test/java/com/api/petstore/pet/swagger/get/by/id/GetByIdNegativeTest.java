package com.api.petstore.pet.swagger.get.by.id;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.api.petstore.swagger.requests.PetRequests;
import com.api.petstore.swagger.requests.Specifications;
import com.api.petstore.data_providers.PetDataProvider;
import com.api.petstore.models.pet.PetModel;
import utils.common.factory.pet.PetFactory;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET;
import static com.api.petstore.swagger.instances.urls.BaseUrls.BASE_URL;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET_STRICT;
import static utils.common.factory.enums.PetTypeVar.FULFILLED_PET;

public class GetByIdNegativeTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeClass
    public void beforeGetByIdTests() {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET);
        PetRequests.post(spec, pet);
        RequestSpecification specToDelete = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        PetRequests.delete(specToDelete);
    }

    @Test
    public static void getPetByNotExistingIdNegative() {
        RequestSpecification specToDelete = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        PetRequests.delete(specToDelete);
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + pet.getId());
        Response response = PetRequests.get(spec);
        Assert.assertEquals(response.getStatusCode(), SC_NOT_FOUND, "Not existing pet exists");
    }

    @Test(dataProvider = "Incorrect_id_for_pets", dataProviderClass = PetDataProvider.class)
    public static void getPetByIncorrectIdNegative(String id) {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PET_STRICT + id);
        Response response = PetRequests.get(spec);
        Assert.assertEquals(response.getStatusCode(), SC_NOT_FOUND, "Incompatible type of id works out");
    }
}