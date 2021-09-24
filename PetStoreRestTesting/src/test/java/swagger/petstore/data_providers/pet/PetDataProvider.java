package swagger.petstore.data_providers.pet;

import org.testng.annotations.DataProvider;

public class PetDataProvider {

    @DataProvider(name = "Correct_pet_statuses")
    public static Object[][] positiveStatusData() {
        return new Object[][] {
                {new String[] {"available"}},
                {new String[] {"pending"}},
                {new String[] {"sold"}}};
    }

    @DataProvider(name = "Incorrect_id_for_pets")
    public static Object[][] getIncorrectIdForPets() {
        return new Object[][]{
                {"asd"},
                {"9929"},
                {"213ddd"},
                {"   2 "}};
    }

    @DataProvider(name = "Incorrect_pet_statuses")
    public static Object[][] getIncorrectPetStatuses() {
        return new Object[][]{
                {new String[] {"availabqele"}},
                {new String[] {"qqqqq"}},
                {new String[] {"12345"}},
                {new String[] {"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq"}},
                {new String[] {"DROP TABLE"}},
                {new String[] {"https://yandex.ru/"}}};
    }
}