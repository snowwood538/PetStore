package utils.wiremock.handler;

import com.api.petstore.models.pet.PetModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonReader {

    public static PetModel readJsonFile(String path) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), PetModel.class);
    }
}