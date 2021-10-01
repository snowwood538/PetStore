package com.api.petstore.pet.wiremock.get.by.id;

import com.api.petstore.data_providers.PetDataProvider;
import com.api.petstore.instances.JsonFilePaths;
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
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import static com.api.petstore.instances.TestingGroups.WIREMOCK_GROUP;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET_STRICT;
import static com.api.petstore.swagger.instances.urls.BaseUrls.MOCK_URL;
import static com.gargoylesoftware.htmlunit.HttpHeader.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;

public class GetByNotValidIDNegativeTest {
    private final String id;
    ResponseDefinitionBuilder mock = new ResponseDefinitionBuilder();

    @Factory(dataProvider = "Incorrect_id_for_pets", dataProviderClass = PetDataProvider.class)
    public GetByNotValidIDNegativeTest(String id) {
        this.id = id;
    }

    @BeforeMethod(groups = WIREMOCK_GROUP)
    public void beforeGetByIdNegativeTests() {
        mock.withStatus(400);
        mock.withHeader(CONTENT_TYPE, "application/json");
        mock.withBodyFile(JsonFilePaths.PATH_TO_RESPONSE_JSON);
        WireMock.stubFor(WireMock.get(PET_STRICT + id).willReturn(mock));
    }

    @Test(groups = WIREMOCK_GROUP)
    public void getPetByIncorrectIdNegative() {
        RequestSpecification spec = Specifications.requestSpecification(MOCK_URL, PET_STRICT + id);
        Response response = PetRequests.get(spec);
        Assert.assertEquals(response.getStatusCode(), SC_BAD_REQUEST, "Incompatible type of id works out");
    }

    @AfterMethod(groups = WIREMOCK_GROUP)
    public void afterGetPetByIncorrectIdNegative() {
        WiremockServer.cleanMock(WireMock.stubFor(WireMock.get(PET_STRICT + id).willReturn(mock)));
    }
}