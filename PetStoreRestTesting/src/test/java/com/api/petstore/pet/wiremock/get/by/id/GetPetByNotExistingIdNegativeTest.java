package com.api.petstore.pet.wiremock.get.by.id;

import com.api.petstore.instances.JsonFilePaths;
import com.api.petstore.models.pet.PetModel;
import com.api.petstore.swagger.requests.PetRequests;
import com.api.petstore.swagger.requests.Specifications;
import com.api.petstore.wiremock.runner.WiremockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.common.factory.pet.PetFactory;

import static com.api.petstore.instances.TestingGroups.WIREMOCK_GROUP;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET_STRICT;
import static com.api.petstore.swagger.instances.urls.BaseUrls.MOCK_URL;
import static com.gargoylesoftware.htmlunit.HttpHeader.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static utils.common.factory.enums.PetTypeVar.FULFILLED_PET;

public class GetPetByNotExistingIdNegativeTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);
    ResponseDefinitionBuilder mock = new ResponseDefinitionBuilder();

    @BeforeMethod(groups = WIREMOCK_GROUP)
    public void beforeGetByIdNegativeTests() {
        mock.withStatus(404);
        mock.withHeader(CONTENT_TYPE, "application/json");
        mock.withBodyFile(JsonFilePaths.PATH_TO_RESPONSE_JSON);
        WireMock.stubFor(WireMock.get(PET_STRICT + pet.getId()).willReturn(mock));
    }

    @Test(groups = WIREMOCK_GROUP)
    public static void getPetByNotExistingIdNegative() {
        RequestSpecification spec = Specifications.requestSpecification(MOCK_URL, PET_STRICT + pet.getId());
        Response response = PetRequests.get(spec);
        Assert.assertEquals(response.getStatusCode(), SC_NOT_FOUND, "Not existing pet exists");
    }

    @AfterMethod(groups = WIREMOCK_GROUP)
    public void afterGetPetByNotExistingIdNegative() {
        WiremockServer.cleanMock(WireMock.stubFor(WireMock.get(PET_STRICT + pet.getId()).willReturn(mock)));
    }
}