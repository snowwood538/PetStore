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
import utils.validator.JsonValidator;

import java.io.FileNotFoundException;
import java.util.List;

public class GetPetByStatusTest {

    @Test(dataProvider = "Correct_pet_statuses", dataProviderClass = PetDataProvider.class)
    public void getPetByStatusCorrectStatuses(String[] status) {
        Response response = GetRequests.getPetsByStatus(status);
        Assert.assertEquals(response.getStatusCode(), 200);
        JsonValidator.validatePetList(response.asString());
    }

    @Test(dataProvider = "Correct_pet_statuses", dataProviderClass = PetDataProvider.class)
    public void responseBodyHasStatusAccordingRequest(String[] status) {
        try {
            JsonMapper mapper = new JsonMapper();
            Response response = GetRequests.getPetsByStatus(status);
            List<Pet> myObjects = mapper.readValue(response.asString(), new TypeReference<>(){});
            for (Pet pet : myObjects) {
                System.out.println(pet.getStatus());
                Assert.assertEquals(pet.getStatus(), status[0]);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}