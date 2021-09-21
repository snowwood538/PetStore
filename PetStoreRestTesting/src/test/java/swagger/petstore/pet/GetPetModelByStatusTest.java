package swagger.petstore.pet;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.Pet;
import swagger.petstore.api_requests.Specifications;
import swagger.petstore.data_providers.PetDataProvider;
import swagger.petstore.models.PetModel;
import utils.validator.JsonValidator;

import java.util.List;

import static swagger.petstore.api_instances.endpoints.PetEndpoints.GET_PETS_BY_STATUS;
import static swagger.petstore.api_instances.queries.PetQueries.petStatus;

public class GetPetModelByStatusTest {

    @Test(dataProvider = "Correct_pet_statuses", dataProviderClass = PetDataProvider.class)
    public void getPetByStatusCorrectStatuses(String[] status) {
        RequestSpecification spec = Specifications.baseGetRequestSpecification("https://petstore.swagger.io/v2", GET_PETS_BY_STATUS);
        Response response = Pet.getPet(spec.queryParam(petStatus, status[0]));
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonValidator.validatePetList(response.asString());
    }

    @SneakyThrows
    @Test(dataProvider = "Correct_pet_statuses", dataProviderClass = PetDataProvider.class)
    public void responseBodyHasStatusAccordingRequest(String[] status) {
        RequestSpecification spec = Specifications.baseGetRequestSpecification("https://petstore.swagger.io/v2", GET_PETS_BY_STATUS);
        Response response = Pet.getPet(spec.queryParam(petStatus, status[0]));
        JsonMapper mapper = new JsonMapper();
        List<PetModel> myObjects = mapper.readValue(response.asString(), new TypeReference<>(){});
        for (PetModel pet : myObjects) {
            Assert.assertEquals(pet.getStatus(), status[0], "The object meets the requirements");
        }
    }
}