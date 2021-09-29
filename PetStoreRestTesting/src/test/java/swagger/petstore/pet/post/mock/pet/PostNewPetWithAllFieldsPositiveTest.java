package swagger.petstore.pet.post.mock.pet;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import swagger.petstore.api.requests.PetRequests;
import swagger.petstore.api.requests.Specifications;
import swagger.petstore.models.PetModel;
import swagger.petstore.wiremock.runner.WiremockServer;
import utils.factory.pet.factory.PetFactory;
import utils.file.writer.JsonWriter;
import utils.validator.JsonValidator;

import static org.apache.http.HttpStatus.SC_OK;
import static swagger.petstore.api.instances.endpoints.PetEndpoints.PET;
import static swagger.petstore.api.instances.urls.BaseUrls.MOCK_URL;
import static swagger.petstore.instances.JsonFilePaths.FULFILLED_PET_JSON;
import static utils.factory.pet.utils.PetTypeVar.FULFILLED_PET;
import static utils.file.instances.JsonInstances.POST_FULFILLED_PET_JSON;

public class PostNewPetWithAllFieldsPositiveTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);
    private static final  ResponseDefinitionBuilder mock = new ResponseDefinitionBuilder();

    @SneakyThrows
    @BeforeMethod(groups = "Wiremock Test Group")
    public void beforePostNewPetWithAllFieldsPositive() {
        JsonWriter.writeJsonFile(pet, POST_FULFILLED_PET_JSON);

        mock.withStatus(200);
        mock.withHeader("Content-Type", "application/json");
        mock.withBodyFile(FULFILLED_PET_JSON);
        WireMock.stubFor(WireMock.post("/pet").willReturn(mock));
    }

    @Test(groups = "Wiremock Test Group")
    public static void postNewPetWithAllFieldsPositive() {
        RequestSpecification spec = Specifications.requestSpecification(MOCK_URL, PET);
        Response response = PetRequests.post(spec, pet);

        Assert.assertEquals(response.getStatusCode(), SC_OK, "Pet creation failed");
        JsonValidator.validatePetObject(response);
    }

    @AfterMethod(groups = "Wiremock Test Group")
    public void afterPostNewPetWithAllFieldsPositive() {
        WiremockServer.resetMappings();
    }
}