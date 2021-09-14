package swagger.petstore.pet;

import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.pet.GetRequests;

public class GetPetByIdTest {

    @Test
    public static void getPetByIdPositive() {
        Assert.assertEquals(GetRequests.getPetById(10).statusCode(), 200);
    }
}
