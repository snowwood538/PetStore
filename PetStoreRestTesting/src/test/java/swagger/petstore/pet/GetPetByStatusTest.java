package swagger.petstore.pet;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import swagger.petstore.api_requests.pet.GetRequests;

public class GetPetByStatusTest {

    @Test(dataProvider = "Correct pet statuses")
    public void getPetByStatusCorrectStatuses(String status, int statusCode) {
        Assert.assertEquals(GetRequests.getPetsByStatus(status).getStatusCode(), statusCode);
    }


    @Test(dataProvider = "Incorrect pet statuses")
    public void getPetByStatusIncorrectStatuses(String status, int statusCode) {
        Assert.assertEquals(GetRequests.getPetsByStatus(status).getStatusCode(), statusCode);
    }

    @Test
    public void getPetStatusWithEmptyStatus() {
        Assert.assertEquals(GetRequests.getPetsByStatus("").getStatusCode(), 404);
    }

    @Test(dataProvider = "Statuses to compare")
    public void responseBodyHasStatusAccordingRequest(String status) {
        Assert.assertTrue(GetRequests.getPetsByStatus(status).getBody().toString().contains(status));
        GetRequests.getPetsByStatus(status).prettyPrint();
    }

    @DataProvider(name = "Statuses to compare")
    public Object[][] dataToCompare() {
        return new Object[][] {
                {"available"},
                {"pending"},
                {"sold"}};
    }

    @DataProvider(name = "Incorrect pet statuses")
    public Object[][] incorrectStatusData() {
        return new Object[][] {
                {"not available", 400},
                {"200",400},
                {"//////",400},
                {"so_ld", 400},
                {"avai lable", 400}};
    }

    @DataProvider(name = "Correct pet statuses")
    public Object[][] positiveStatusData() {
        return new Object[][] {
                {"available", 200},
                {"pending",200},
                {"sold",200}};
    }
}