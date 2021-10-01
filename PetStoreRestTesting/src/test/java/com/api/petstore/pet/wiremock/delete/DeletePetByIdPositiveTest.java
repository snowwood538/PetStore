package com.api.petstore.pet.wiremock.delete;

import com.api.petstore.instances.JsonFilePaths;
import com.api.petstore.models.pet.PetModel;
import com.api.petstore.models.response.ResponseModel;
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
import utils.common.factory.response.ResponseFactory;
import utils.common.validator.JsonValidator;
import utils.wiremock.handler.JsonWriter;

import static com.api.petstore.instances.TestingGroups.WIREMOCK_GROUP;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET_STRICT;
import static com.api.petstore.swagger.instances.urls.BaseUrls.MOCK_URL;
import static com.gargoylesoftware.htmlunit.HttpHeader.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static utils.common.factory.enums.PetTypeVar.FULFILLED_PET;
import static utils.common.factory.enums.ResponseTypes.NOT_FOUND;
import static utils.wiremock.instances.JsonInstances.RESPONSE_JSON;

public class DeletePetByIdPositiveTest {
    private static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);
    private static final ResponseModel response = ResponseFactory.createResponse(NOT_FOUND);
    ResponseDefinitionBuilder mock = new ResponseDefinitionBuilder();

    @SneakyThrows
    @BeforeMethod(groups = WIREMOCK_GROUP)
    public void beforeDeleteNegativeTests() {
        JsonWriter.writeJsonResponse(response, RESPONSE_JSON);

        mock.withStatus(404);
        mock.withHeader(CONTENT_TYPE, "application/json");
        mock.withBodyFile(JsonFilePaths.PATH_TO_RESPONSE_JSON);
        WireMock.stubFor(WireMock.delete(PET_STRICT + pet.getId()).willReturn(mock));
    }

    @Test(groups = WIREMOCK_GROUP)
    public static void deletePetByIdPositiveTest() {
        RequestSpecification spec = Specifications.requestSpecification(MOCK_URL, PetEndpoints.PET_STRICT + pet.getId());
        Response response = PetRequests.delete(spec);
        Assert.assertEquals(response.getStatusCode(), SC_NOT_FOUND, "Couldn't delete existing pet by id");
        JsonValidator.validateResponse(response);
    }

    @AfterMethod(groups = WIREMOCK_GROUP)
    public void afterDeletePetByIdPositiveTest() {
        WiremockServer.cleanMock(WireMock.stubFor(WireMock.delete(PET_STRICT + pet.getId()).willReturn(mock)));
    }
}