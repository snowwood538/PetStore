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

    @DataProvider(name = "Existing_id_for_pets")
    public static Object[][] getExistingIdForPets() {
        return new Object[][]{
                {1},
                {10},
                {100},
                {1000}};
    }

    @DataProvider(name = "Not_existing_id_for_pets")
    public static Object[][] getNotExistingIdForPets() {
        return new Object[][]{
                {77},
                {999},
                {1000},
                {56798}};
    }

    @DataProvider(name = "Incorrect_id_for_pets")
    public static Object[][] getIncorrectIdForPets() {
        return new Object[][]{
                {""},
                {"asd"},
                {"999"},
                {"213ddd"},
                {"   2 "}};
    }
}
