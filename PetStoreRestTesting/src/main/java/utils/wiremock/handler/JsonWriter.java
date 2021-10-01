package utils.wiremock.handler;

import com.api.petstore.models.response.ResponseModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.api.petstore.models.pet.PetModel;

import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {

    public static void writeJsonFile(PetModel pet, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String convertedPet = mapper.writeValueAsString(pet);
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(convertedPet);
        fileWriter.flush();
    }

    public static void writeJsonResponse(ResponseModel responseModel, String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String convertedPet = mapper.writeValueAsString(responseModel);
        FileWriter fileWriter = new FileWriter(path);
        fileWriter.write(convertedPet);
        fileWriter.flush();
    }
}