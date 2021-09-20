package swagger.petstore.pet;

import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.pet.GetRequests;
import swagger.petstore.data_providers.PetDataProvider;
import utils.validator.JsonValidator;

import java.io.FileNotFoundException;

public class GetPetByIdTest {

    @Test(dataProvider = "Correct_pet_id", dataProviderClass = PetDataProvider.class)
    public static void getPetByIdPositive(int id) {
        Assert.assertEquals(GetRequests.getPetById(id).statusCode(), 200);
        JsonValidator.validatePetObject(GetRequests.getPetById(id));
    }
}
