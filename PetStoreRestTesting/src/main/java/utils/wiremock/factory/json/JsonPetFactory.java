package utils.wiremock.factory.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Nullable;
import swagger.petstore.models.swagger.PetModel;

public class JsonPetFactory {
    @Nullable
    public static String getJsonObjectPet(PetModel pet) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(pet);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
