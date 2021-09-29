package utils.file.writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import swagger.petstore.models.PetModel;

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
}
