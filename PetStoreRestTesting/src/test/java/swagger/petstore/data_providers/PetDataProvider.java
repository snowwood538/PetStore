package swagger.petstore.data_providers;

import org.testng.annotations.DataProvider;

public class PetDataProvider {

    @DataProvider(name = "Correct pet statuses")
    public static Object[][] positiveStatusData() {
        return new Object[][] {
                {new String[] {"available"}},
                {new String[] {"pending"}},
                {new String[] {"sold"}}};
    }
}
