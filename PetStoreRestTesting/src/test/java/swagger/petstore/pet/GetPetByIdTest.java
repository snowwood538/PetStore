package swagger.petstore.pet;

import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.pet.GetRequests;
import utils.JsonValidator;

import java.io.FileNotFoundException;

public class GetPetByIdTest {

    @Test
    public static void getPetByIdPositive() throws FileNotFoundException {
        Assert.assertEquals(GetRequests.getPetById(1).statusCode(), 200);
        JsonValidator.validatePetObject(GetRequests.getPetById(1));
    }
}
