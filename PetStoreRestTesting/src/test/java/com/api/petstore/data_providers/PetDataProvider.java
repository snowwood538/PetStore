package com.api.petstore.data_providers;

import org.testng.annotations.DataProvider;

import static com.api.petstore.swagger.instances.file_paths.Paths.DOC_FILE_PATH;
import static com.api.petstore.swagger.instances.file_paths.Paths.HTML_FILE_PATH;
import static com.api.petstore.swagger.instances.file_paths.Paths.TEXT_FILE_PATH;

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
                {"213ddd"}};
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

    @DataProvider(name = "Path_to_files_to_add_images")
    public static Object[][] getPathsToFiles() {
        return new Object[][]{
                {TEXT_FILE_PATH},
                {DOC_FILE_PATH},
                {HTML_FILE_PATH}};
    }
}