package swagger.petstore.pet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.Specifications;
import swagger.petstore.api_requests.pet.GetRequests;
import swagger.petstore.data_providers.PetDataProvider;
import swagger.petstore.models.Pet;
import utils.validator.JsonValidator;

import java.io.FileNotFoundException;
import java.util.List;

import static swagger.petstore.api_instances.endpoints.PetEndpoints.GET_PETS_BY_STATUS;
import static swagger.petstore.api_instances.queries.PetQueries.petStatus;

public class GetPetByStatusTest {

    @Test(dataProvider = "Correct pet statuses", dataProviderClass = PetDataProvider.class)
    public void getPetByStatusCorrectStatuses(String[] status) throws FileNotFoundException {
        RequestSpecification spec = Specifications.baseGetRequestSpecification("https://petstore.swagger.io/v2", GET_PETS_BY_STATUS);
        Response response = GetRequests.getPet(spec.queryParam(petStatus, status[0]));
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonValidator.validatePetList(response.asString());
    }

    @SneakyThrows
    @Test(dataProvider = "Correct pet statuses", dataProviderClass = PetDataProvider.class)
    public void responseBodyHasStatusAccordingRequest(String[] status) {
        RequestSpecification spec = Specifications.baseGetRequestSpecification("https://petstore.swagger.io/v2", GET_PETS_BY_STATUS);
        Response response = GetRequests.getPet(spec.queryParam(petStatus, status[0]));
        JsonMapper mapper = new JsonMapper();
        List<Pet> myObjects = mapper.readValue(response.asString(), new TypeReference<>(){});
        for (Pet pet : myObjects) {
            Assert.assertEquals(pet.getStatus(), status[0]);
        }
    }
}