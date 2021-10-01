package com.api.petstore.pet.wiremock.delete;

import com.api.petstore.instances.JsonFilePaths;
import com.api.petstore.models.response.ResponseModel;
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
import utils.common.factory.response.ResponseFactory;
import utils.common.validator.JsonValidator;
import utils.wiremock.handler.JsonWriter;

import static com.api.petstore.instances.TestingGroups.WIREMOCK_GROUP;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET_STRICT;
import static com.api.petstore.swagger.instances.urls.BaseUrls.MOCK_URL;
import static com.gargoylesoftware.htmlunit.HttpHeader.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static utils.common.factory.enums.ResponseTypes.NOT_ALLOWED;
import static utils.wiremock.instances.JsonInstances.RESPONSE_JSON;

public class DeleteRequestWithEmptyQueryTest {
    private static final ResponseModel response = ResponseFactory.createResponse(NOT_ALLOWED);
    ResponseDefinitionBuilder mock = new ResponseDefinitionBuilder();

    @SneakyThrows
    @BeforeMethod(groups = WIREMOCK_GROUP)
    public void beforeDeleteNegativeTests() {
        JsonWriter.writeJsonResponse(response, RESPONSE_JSON);

        mock.withStatus(405);
        mock.withHeader(CONTENT_TYPE, "application/json");
        mock.withBodyFile(JsonFilePaths.PATH_TO_RESPONSE_JSON);
        WireMock.stubFor(WireMock.delete(PET_STRICT).willReturn(mock));
    }

    @Test(groups = WIREMOCK_GROUP)
    public static void sendDeleteRequestWithEmptyQueryTest() {
        RequestSpecification spec = Specifications.requestSpecification(MOCK_URL, PET_STRICT);
        Response response = PetRequests.delete(spec);
        Assert.assertEquals(response.getStatusCode(), SC_METHOD_NOT_ALLOWED, "Empty query doesn't invoke an error");
        JsonValidator.validateResponse(response);
    }

    @AfterMethod(groups = WIREMOCK_GROUP)
    public void afterDeletePetByIdPositiveTest() {
        WiremockServer.cleanMock(WireMock.stubFor(WireMock.delete(PET_STRICT).willReturn(mock)));
    }
}
