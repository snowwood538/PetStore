package swagger.petstore.pet.get.by.id.mock;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import swagger.petstore.api.requests.PetRequests;
import swagger.petstore.api.requests.Specifications;
import swagger.petstore.data_providers.PetDataProvider;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static swagger.petstore.api.instances.endpoints.PetEndpoints.PET_STRICT;
import static swagger.petstore.api.instances.urls.BaseUrls.MOCK_URL;
import static swagger.petstore.instances.JsonFilePaths.INVALID_ID_PET_JSON;

public class GetByNotValidIDNegativeTest {
    private final String id;

    @Factory(dataProvider = "Incorrect_id_for_pets", dataProviderClass = PetDataProvider.class)
    public GetByNotValidIDNegativeTest(String id) {
        this.id = id;
    }

    @BeforeMethod(groups = "Wiremock Test Group")
    public void beforeGetByIdNegativeTests() {
        ResponseDefinitionBuilder mock = new ResponseDefinitionBuilder();
        mock.withStatus(400);
        mock.withHeader("Content-Type", "application/json");
        mock.withBodyFile(INVALID_ID_PET_JSON);
        WireMock.stubFor(WireMock.get("/pet/" + id).willReturn(mock));
    }

    @Test(groups = "Wiremock Test Group")
    public void getPetByIncorrectIdNegative() {
        RequestSpecification spec = Specifications.requestSpecification(MOCK_URL, PET_STRICT + id);
        Response response = PetRequests.get(spec);
        Assert.assertEquals(response.getStatusCode(), SC_BAD_REQUEST, "Incompatible type of id works out");
    }
}
