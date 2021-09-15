package utils;

import io.restassured.response.Response;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ResponseFileWriter {

    private ResponseFileWriter() {
    }

    public static void writeResponse(Response response) {
        File file = new File("src/main/resources/Response.json");
        try (FileWriter writer = new FileWriter(file);) {
            writer.write(response.body().prettyPrint());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
