package swagger.petstore.pet.wiremock.get.by.id;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import swagger.petstore.api.requests.PetRequests;
import swagger.petstore.api.requests.Specifications;
import swagger.petstore.models.swagger.PetModel;
import utils.swagger.factory.PetFactory;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static swagger.petstore.api.instances.endpoints.PetEndpoints.PET_STRICT;
import static swagger.petstore.instances.JsonFilePaths.NOT_FOUND_PET_JSON;
import static swagger.petstore.wiremock.urls.BaseUrls.MOCK_URL;
import static utils.common.generator.PetTypeVar.FULFILLED_PET;

public class GetPetByNotExistingIdNegativeTest {
    public static final PetModel pet = PetFactory.createNewPet(FULFILLED_PET);

    @BeforeMethod(groups = "Wiremock Test Group")
    public void beforeGetByIdNegativeTests() {
        ResponseDefinitionBuilder mock = new ResponseDefinitionBuilder();
        mock.withStatus(404);
        mock.withHeader("Content-Type", "application/json");
        mock.withBodyFile(NOT_FOUND_PET_JSON);
        WireMock.stubFor(WireMock.get("/pet/" + pet.getId()).willReturn(mock));
    }

    @Test(groups = "Wiremock Test Group")
    public static void getPetByNotExistingIdNegative() {
        RequestSpecification spec = Specifications.requestSpecification(MOCK_URL, PET_STRICT + pet.getId());
        Response response = PetRequests.get(spec);
        Assert.assertEquals(response.getStatusCode(), SC_NOT_FOUND, "Not existing pet exists");
    }
}
