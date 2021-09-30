package utils.common.validator;

import io.restassured.response.Response;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

import static utils.common.validator.ValidationTemplatePaths.PATH_TO_PETLIST_TEMPLATE;
import static utils.common.validator.ValidationTemplatePaths.PATH_TO_PET_TEMPLATE;
import static utils.common.validator.ValidationTemplatePaths.PATH_TO_RESPONSE_TEMPLATE;

public class JsonValidator {

    public static void validateObject(Response response) {
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

    public static void validateList(String response) {
        try {
            JSONObject jsonSchema = new JSONObject(new JSONTokener(new FileInputStream(PATH_TO_PETLIST_TEMPLATE)));
            JSONArray jsonSubject = new JSONArray(new JSONTokener(response));
            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonSubject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void validateResponse(Response response) {
        try {
            JSONObject jsonSchema = new JSONObject(new JSONTokener(new FileInputStream(PATH_TO_RESPONSE_TEMPLATE)));
            JSONObject jsonSubject = new JSONObject(new JSONTokener(response.asString()));
            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonSubject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}