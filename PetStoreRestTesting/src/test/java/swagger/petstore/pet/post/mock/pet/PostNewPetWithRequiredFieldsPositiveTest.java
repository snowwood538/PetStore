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
import static swagger.petstore.instances.JsonFilePaths.BASE_FIELD_PET_JSON;
import static utils.factory.pet.utils.PetTypeVar.BASE_PET;
import static utils.file.instances.JsonInstances.POST_BASE_PET_JSON;

public class PostNewPetWithRequiredFieldsPositiveTest {
    public static final PetModel pet = PetFactory.createNewPet(BASE_PET);
    ResponseDefinitionBuilder mock = new ResponseDefinitionBuilder();

    @SneakyThrows
    @BeforeMethod(groups = "Wiremock Test Group")
    public void beforePostNewPetWithAllFieldsPositive() {
        JsonWriter.writeJsonFile(pet, POST_BASE_PET_JSON);

        mock.withStatus(200);
        mock.withHeader("Content-Type", "application/json");
        mock.withBodyFile(BASE_FIELD_PET_JSON);
        WireMock.stubFor(WireMock.post("/pet").willReturn(mock));
    }

    @Test(groups = "Wiremock Test Group")
    public static void postNewPetWithRequiredFieldsPositive() {
        RequestSpecification spec = Specifications.requestSpecification(MOCK_URL, PET);
        Response response = PetRequests.post(spec, pet);
        Assert.assertEquals(response.getStatusCode(), SC_OK, "Pet creation failed");
        JsonValidator.validatePetObject(response);
    }

    @AfterMethod(groups = "Wiremock Test Group")
    public void afterPostNewPetWithRequiredFieldsPositive() {
        WiremockServer.resetMappings();
        WiremockServer.cleanMock(WireMock.stubFor(WireMock.post("/pet").willReturn(mock)));
    }
}
