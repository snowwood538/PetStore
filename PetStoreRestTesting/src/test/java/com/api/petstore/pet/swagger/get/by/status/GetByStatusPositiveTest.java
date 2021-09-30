package com.api.petstore.pet.swagger.get.by.status;

import com.api.petstore.models.pet.PetModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.api.petstore.swagger.requests.PetRequests;
import com.api.petstore.swagger.requests.Specifications;
import com.api.petstore.data_providers.PetDataProvider;
import utils.common.validator.JsonValidator;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static com.api.petstore.swagger.instances.urls.BaseUrls.BASE_URL;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PETS_BY_STATUS;
import static com.api.petstore.swagger.instances.queries.PetQueries.PET_STATUS;

public class GetByStatusPositiveTest {

    @Test(dataProvider = "Correct_pet_statuses", dataProviderClass = PetDataProvider.class)
    public void getPetByStatusCorrectStatuses(String[] status) {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PETS_BY_STATUS);
        Response response = PetRequests.get(spec.queryParam(PET_STATUS, status[0]));
        Assert.assertEquals(response.getStatusCode(), SC_OK);
        JsonValidator.validateList(response.toString());
    }

    @SneakyThrows
    @Test(dataProvider = "Correct_pet_statuses", dataProviderClass = PetDataProvider.class)
    public void responseBodyHasStatusAccordingRequest(String[] status) {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PETS_BY_STATUS);
        Response response = PetRequests.get(spec.queryParam(PET_STATUS, status[0]));
        JsonMapper mapper = new JsonMapper();
        List<PetModel> myObjects = mapper.readValue(response.asString(), new TypeReference<>(){});
        for (PetModel pet : myObjects) {
            Assert.assertEquals(pet.getStatus(), status[0], "One of objects has incorrect parameters");
        }
    }
}