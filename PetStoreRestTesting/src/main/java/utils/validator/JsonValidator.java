package utils.validator;

import io.restassured.response.Response;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

import static utils.validator.ValidationTemplatePaths.PATH_TO_PETLIST_TEMPLATE;
import static utils.validator.ValidationTemplatePaths.PATH_TO_PET_TEMPLATE;

public class JsonValidator {

    public static void validatePetObject(Response response) {
        try {
            String requestResponse = response.asString();
            JSONObject jsonSchema = new JSONObject(new JSONTokener(new FileInputStream(PATH_TO_PET_TEMPLATE)));
            JSONObject jsonSubject = new JSONObject(new JSONTokener(requestResponse));
            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonSubject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void validatePetList(String requestResponse) {
        try {
            JSONObject jsonSchema = new JSONObject(new JSONTokener(new FileInputStream(PATH_TO_PETLIST_TEMPLATE)));
            JSONArray jsonSubject = new JSONArray(new JSONTokener(requestResponse));
            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonSubject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}