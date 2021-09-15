package utils;

import io.restassured.response.Response;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class JsonValidator {
    private static final String PATH_TO_TEMPLATE = "src/main/resources/Pet.json";
    private static final String PATH_TO_EXAMPLE = "src/main/resources/PetExample.json";
    private static final String PATH_TO_RESPONSE_FILE = "src/main/resources/Response.json";

    private JsonValidator() {

    }

    public static void validateFile(Response response) throws FileNotFoundException {
        ResponseFileWriter.writeResponse(response);
        JSONObject jsonSchema = new JSONObject(
                new JSONTokener(new FileInputStream(PATH_TO_TEMPLATE)));
        JSONObject jsonSubject = new JSONObject(
                new JSONTokener(new FileInputStream(PATH_TO_RESPONSE_FILE)));

        Schema schema = SchemaLoader.load(jsonSchema);
        schema.validate(jsonSubject);
    }
}