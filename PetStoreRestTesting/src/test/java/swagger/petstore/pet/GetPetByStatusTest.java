package swagger.petstore.pet;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.pet.GetRequests;
import swagger.petstore.data_providers.PetDataProvider;
import swagger.petstore.models.Pet;

public class GetPetByStatusTest {

    @Test(dataProvider = "Correct pet statuses", dataProviderClass = PetDataProvider.class)
    public void getPetByStatusCorrectStatuses(String status, int statusCode) {
        Assert.assertEquals(GetRequests.getPetsByStatus(status).getStatusCode(), statusCode);
    }


    @Test(dataProvider = "Incorrect pet statuses", dataProviderClass = PetDataProvider.class)
    public void getPetByStatusIncorrectStatuses(String status, int statusCode) {
        Assert.assertEquals(GetRequests.getPetsByStatus(status).getStatusCode(), statusCode);
    }

    @Test
    public void getPetStatusWithEmptyStatus() {
        Assert.assertEquals(GetRequests.getPetsByStatus("").getStatusCode(), 404);
    }

    @Test(dataProvider = "Statuses to compare", dataProviderClass = PetDataProvider.class)
    public void responseBodyHasStatusAccordingRequest(String status) {
        Response response = GetRequests.getPetsByStatus(status);
        String actualStatus = response.as(Pet.class).getStatus();
        Assert.assertEquals(actualStatus, status);
    }
}