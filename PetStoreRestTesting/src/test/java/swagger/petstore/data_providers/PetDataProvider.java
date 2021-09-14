package swagger.petstore.data_providers;

import org.testng.annotations.DataProvider;

public class PetDataProvider {

    @DataProvider(name = "Statuses to compare")
    public static Object[][] dataToCompare() {
        return new Object[][] {
                {"available"},
                {"pending"},
                {"sold"}};
    }

    @DataProvider(name = "Incorrect pet statuses")
    public static Object[][] incorrectStatusData() {
        return new Object[][] {
                {"not available", 400},
                {"200",400},
                {"//////",400},
                {"so_ld", 400},
                {"avai lable", 400}};
    }

    @DataProvider(name = "Correct pet statuses")
    public static Object[][] positiveStatusData() {
        return new Object[][] {
                {"available", 200},
                {"pending",200},
                {"sold",200}};
    }
}
