package swagger.petstore.pet.get.by.id.mock;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import swagger.petstore.api.requests.PetRequests;
import swagger.petstore.api.requests.Specifications;
import swagger.petstore.models.PetModel;
import swagger.petstore.wiremock.factory.json.JsonPetFactory;
import utils.factory.pet.factory.PetFactory;
import utils.validator.JsonValidator;

import static org.apache.http.HttpStatus.SC_OK;
import static swagger.petstore.api.instances.endpoints.PetEndpoints.PET_STRICT;
import static swagger.petstore.api.instances.urls.BaseUrls.MOCK_URL;
import static utils.factory.pet.utils.PetTypeVar.FULFILLED_PET;

public class GetByIdPositiveTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeTest(groups = "Wiremock Test Group")
    public void beforeGetByIdPositiveTests() {
        ResponseDefinitionBuilder mock = new ResponseDefinitionBuilder();
        mock.withStatus(200);
        mock.withHeader("Content-Type", "application/json");
        mock.withBody(JsonPetFactory.getJsonObjectPet(pet));
        WireMock.stubFor(WireMock.get("/pet/" + pet.getId()).willReturn(mock));
    }

    @Test(groups = "Wiremock Test Group")
    public static void getPetByIdPositive() {
        RequestSpecification spec = Specifications.requestSpecification(MOCK_URL, PET_STRICT + pet.getId());
        Response response = PetRequests.get(spec);
        Assert.assertEquals(response.getStatusCode(), SC_OK, "Couldn't get pet by id");
        JsonValidator.validatePetObject(response);
    }
}
