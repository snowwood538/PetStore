package utils;

import io.restassured.response.Response;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class JsonValidator {
    private static final String PATH_TO_PET_TEMPLATE = "src/main/resources/Pet.json";
    private static final String PATH_TO_PETLIST_TEMPLATE = "src/main/resources/PetList.json";


    private JsonValidator() {

    }

    public static void validatePetObject(Response response) throws FileNotFoundException {
        String requestResponse = response.asString();
        JSONObject jsonSchema = new JSONObject(new JSONTokener(new FileInputStream(PATH_TO_PET_TEMPLATE)));
        JSONObject jsonSubject = new JSONObject(new JSONTokener(requestResponse));
        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }

    public static void validatePetList(String requestResponse) throws FileNotFoundException {
        JSONObject jsonSchema = new JSONObject(new JSONTokener(new FileInputStream(PATH_TO_PETLIST_TEMPLATE)));
        JSONArray jsonSubject = new JSONArray(new JSONTokener(requestResponse));
        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }
}