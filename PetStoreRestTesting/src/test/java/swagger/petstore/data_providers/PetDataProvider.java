package swagger.petstore.data_providers;

import org.testng.annotations.DataProvider;

public class PetDataProvider {

    @DataProvider(name = "Correct_pet_statuses")
    public static Object[][] positiveStatusData() {
        return new Object[][] {
                {new String[] {"available"}},
                {new String[] {"pending"}},
                {new String[] {"sold"}}};
    }

    @DataProvider(name = "Correct_pet_id")
    public static Object[][] positiveIdData() {
        return new Object[][] {
                {10},
                {100},
                {1000}};
    }
}
