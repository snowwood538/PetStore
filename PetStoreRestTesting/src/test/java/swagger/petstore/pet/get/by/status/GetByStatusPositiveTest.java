package swagger.petstore.pet.get.by.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api.requests.PetRequests;
import swagger.petstore.api.requests.Specifications;
import swagger.petstore.data_providers.pet.PetDataProvider;
import swagger.petstore.models.PetModel;
import utils.validator.JsonValidator;

import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static swagger.petstore.api.instances.urls.BaseUrls.BASE_URL;
import static swagger.petstore.api.instances.endpoints.PetEndpoints.PETS_BY_STATUS;
import static swagger.petstore.api.instances.queries.PetQueries.PET_STATUS;

public class GetByStatusPositiveTest {

    @Test(dataProvider = "Correct_pet_statuses", dataProviderClass = PetDataProvider.class)
    public void getPetByStatusCorrectStatuses(String[] status) {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PETS_BY_STATUS);
        Response response = PetRequests.get(spec.queryParam(PET_STATUS, status[0]));
        Assert.assertEquals(response.getStatusCode(), SC_OK);
        JsonValidator.validatePetList(response.asString());
    }

    @SneakyThrows
    @Test(dataProvider = "Correct_pet_statuses", dataProviderClass = PetDataProvider.class)
    public void responseBodyHasStatusAccordingRequest(String[] status) {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PETS_BY_STATUS);
        Response response = PetRequests.get(spec.queryParam(PET_STATUS, status[0]));
        JsonMapper mapper = new JsonMapper();
        List<PetModel> myObjects = mapper.readValue(response.asString(), new TypeReference<>(){});
        for (PetModel pet : myObjects) {
            Assert.assertEquals(pet.getStatus(), status[0], "One of objects has incorrect parameters");
        }
    }
}