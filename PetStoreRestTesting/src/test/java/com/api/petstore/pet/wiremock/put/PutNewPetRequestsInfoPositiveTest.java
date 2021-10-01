package com.api.petstore.pet.wiremock.put;

import com.api.petstore.instances.JsonFilePaths;
import com.api.petstore.models.pet.PetModel;
import com.api.petstore.swagger.requests.PetRequests;
import com.api.petstore.swagger.requests.Specifications;
import com.api.petstore.wiremock.runner.WiremockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.common.factory.pet.PetFactory;
import utils.common.validator.JsonValidator;
import utils.wiremock.handler.JsonReader;
import utils.wiremock.handler.JsonWriter;

import java.util.List;

import static com.api.petstore.instances.TestingGroups.WIREMOCK_GROUP;
import static com.api.petstore.swagger.instances.endpoints.PetEndpoints.PET;
import static com.api.petstore.swagger.instances.urls.BaseUrls.MOCK_URL;
import static com.gargoylesoftware.htmlunit.HttpHeader.CONTENT_TYPE;
import static org.apache.http.HttpStatus.SC_OK;
import static utils.common.factory.enums.PetTypeVar.FULFILLED_PET;
import static utils.wiremock.instances.JsonInstances.EXAMPLE_FOR_PUT_JSON;

public class PutNewPetRequestsInfoPositiveTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);
    ResponseDefinitionBuilder mock = new ResponseDefinitionBuilder();

    @SneakyThrows
    @BeforeMethod(groups = WIREMOCK_GROUP)
    public void beforePutNewPetInfoPositive() {
        JsonWriter.writeJsonFile(pet, EXAMPLE_FOR_PUT_JSON);
        mock.withStatus(200);
        mock.withHeader(CONTENT_TYPE, String.valueOf(ContentType.JSON));
        mock.withBodyFile(JsonFilePaths.PATH_TO_UPDATED_PET_JSON);
        WireMock.stubFor(WireMock.put(PET).willReturn(mock));
    }

    @SneakyThrows
    @Test(groups = WIREMOCK_GROUP)
    public static void putNewPetInfoPositive() {
        PetModel petFromJson = JsonReader.readJsonFile(EXAMPLE_FOR_PUT_JSON);
        petFromJson.setName("NewName");
        petFromJson.setPhotoUrls(List.of("NewURL"));
        JsonWriter.writeJsonFile(petFromJson, EXAMPLE_FOR_PUT_JSON);
        RequestSpecification spec = Specifications.requestSpecification(MOCK_URL, PET);
        Response response = PetRequests.put(spec, petFromJson);

        Assert.assertEquals(response.getStatusCode(), SC_OK, "Setting new info has failed");
        JsonValidator.validateObject(response);
    }

    @AfterMethod(groups = WIREMOCK_GROUP)
    public void afterPutNewPetInfoPositive() {
        WiremockServer.cleanMock(WireMock.stubFor(WireMock.put(PET).willReturn(mock)));
    }
}