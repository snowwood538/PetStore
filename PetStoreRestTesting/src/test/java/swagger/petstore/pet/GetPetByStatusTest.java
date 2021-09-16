package swagger.petstore.pet;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.pet.GetRequests;
import swagger.petstore.data_providers.PetDataProvider;
import swagger.petstore.models.Pet;
import utils.JsonValidator;

import java.io.FileNotFoundException;
import java.util.List;

public class GetPetByStatusTest {

    @Test(dataProvider = "Correct pet statuses", dataProviderClass = PetDataProvider.class)
    public void getPetByStatusCorrectStatuses(String[] status, int statusCode) throws FileNotFoundException {
        Response response = GetRequests.getPetsByStatus(status);
        Assert.assertEquals(response.getStatusCode(), statusCode);
        JsonValidator.validatePetList(response.asString());
    }

    @Test(dataProvider = "Statuses to compare", dataProviderClass = PetDataProvider.class)
    public void responseBodyHasStatusAccordingRequest(String[] status) throws JsonProcessingException {
        JsonMapper mapper = new JsonMapper();
        Response response = GetRequests.getPetsByStatus(status);
        List<Pet> myObjects = mapper.readValue(response.asString(), new TypeReference<>(){});
        for (Pet pet : myObjects) {
            System.out.println(pet.getStatus());
            Assert.assertEquals(pet.getStatus(), status[0]);
        }
    }
}