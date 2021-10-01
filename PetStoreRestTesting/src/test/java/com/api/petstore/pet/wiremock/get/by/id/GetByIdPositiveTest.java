package com.api.petstore.pet.wiremock.get.by.id;

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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.common.factory.pet.PetFactory;
import utils.common.validator.JsonValidator;

import static com.api.petstore.instances.TestingGroups.WIREMOCK_GROUP;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET_STRICT;
import static com.api.petstore.swagger.instances.urls.BaseUrls.MOCK_URL;
import static com.gargoylesoftware.htmlunit.HttpHeader.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_OK;
import static utils.common.factory.enums.PetTypeVar.FULFILLED_PET;

public class GetByIdPositiveTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);
    ResponseDefinitionBuilder mock = new ResponseDefinitionBuilder();

    @BeforeTest(groups = WIREMOCK_GROUP)
    public void beforeGetByIdPositiveTests() {
        mock.withStatus(200);
        mock.withHeader(CONTENT_TYPE, "application/json");
        mock.withBodyFile(JsonFilePaths.PATH_TO_FULFILLED_PET_JSON);
        WireMock.stubFor(WireMock.get(PET_STRICT + pet.getId()).willReturn(mock));
    }

    @Test(groups = WIREMOCK_GROUP)
    public static void getPetByIdPositive() {
        RequestSpecification spec = Specifications.requestSpecification(MOCK_URL, PetEndpoints.PET_STRICT + pet.getId());
        Response response = PetRequests.get(spec);
        Assert.assertEquals(response.getStatusCode(), SC_OK, "Couldn't get pet by id");
        JsonValidator.validateObject(response);
    }

    @AfterMethod(groups = WIREMOCK_GROUP)
    public void afterGetPetByIdPositive() {
        WiremockServer.cleanMock(WireMock.stubFor(WireMock.get(PET_STRICT + pet.getId()).willReturn(mock)));
    }
}