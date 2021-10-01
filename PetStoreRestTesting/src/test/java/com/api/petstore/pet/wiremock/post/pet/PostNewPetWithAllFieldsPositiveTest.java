package com.api.petstore.pet.wiremock.post.pet;

import com.api.petstore.instances.JsonFilePaths;
import com.api.petstore.models.pet.PetModel;
import com.api.petstore.swagger.instances.endpoints.PetEndpoints;
import com.api.petstore.swagger.requests.PetRequests;
import com.api.petstore.swagger.requests.Specifications;
import com.api.petstore.wiremock.runner.WiremockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.common.factory.pet.PetFactory;
import utils.common.validator.JsonValidator;
import utils.wiremock.handler.JsonWriter;

import static com.api.petstore.instances.TestingGroups.WIREMOCK_GROUP;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET;
import static com.api.petstore.swagger.instances.urls.BaseUrls.MOCK_URL;
import static com.gargoylesoftware.htmlunit.HttpHeader.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_OK;
import static utils.common.factory.enums.PetTypeVar.FULFILLED_PET;
import static utils.wiremock.instances.JsonInstances.POST_FULFILLED_PET_JSON;

public class PostNewPetWithAllFieldsPositiveTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);
    private static final  ResponseDefinitionBuilder mock = new ResponseDefinitionBuilder();

    @SneakyThrows
    @BeforeMethod(groups = WIREMOCK_GROUP)
    public void beforePostNewPetWithAllFieldsPositive() {
        JsonWriter.writeJsonFile(pet, POST_FULFILLED_PET_JSON);

        mock.withStatus(200);
        mock.withHeader(CONTENT_TYPE, "application/json");
        mock.withBodyFile(JsonFilePaths.PATH_TO_FULFILLED_PET_JSON);
        WireMock.stubFor(WireMock.post(PET).willReturn(mock));
    }

    @Test(groups = WIREMOCK_GROUP)
    public static void postNewPetWithAllFieldsPositive() {
        RequestSpecification spec = Specifications.requestSpecification(MOCK_URL, PetEndpoints.PET);
        Response response = PetRequests.post(spec, pet);

        Assert.assertEquals(response.getStatusCode(), SC_OK, "Pet creation failed");
        JsonValidator.validateObject(response);
    }

    @AfterMethod(groups = WIREMOCK_GROUP)
    public void afterPostNewPetWithAllFieldsPositive() {
        WiremockServer.cleanMock(WireMock.stubFor(WireMock.post(PET).willReturn(mock)));
    }
}