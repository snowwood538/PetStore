package utils.wiremock.factory.json;

import com.api.petstore.models.pet.PetModel;
import com.api.petstore.models.response.ResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.Nullable;

public class JsonFactory {
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

    @Nullable
    public static String getJsonObjectResponse(ResponseModel response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}