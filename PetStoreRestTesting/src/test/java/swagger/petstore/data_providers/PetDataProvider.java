package swagger.petstore.data_providers;

import org.testng.annotations.DataProvider;

public class PetDataProvider {

    @DataProvider(name = "Statuses to compare")
    public static Object[][] dataToCompare() {
        return new Object[][] {
                {new String[] {"available"}},
                {new String[] {"pending"}},
                {new String[] {"sold"}}};
    }

    @DataProvider(name = "Incorrect pet statuses")
    public static Object[][] incorrectStatusData() {
        return new Object[][] {
                {new String[] {"not available"}, 400},
                {new String[] {"200"},400},
                {new String[] {"//////"},400},
                {new String[] {"so_ld"}, 400},
                {new String[] {"avai lable"}, 400}};
    }

    @DataProvider(name = "Correct pet statuses")
    public static Object[][] positiveStatusData() {
        return new Object[][] {
                {new String[] {"available"}, 200},
                {new String[] {"pending"},200},
                {new String[] {"sold"},200}};
    }
}
