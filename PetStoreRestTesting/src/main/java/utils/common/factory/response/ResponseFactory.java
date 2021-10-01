package utils.common.factory.response;

import com.api.petstore.models.response.ResponseModel;
import utils.common.factory.response.types.InvalidId;
import utils.common.factory.response.types.NotAllowed;
import utils.common.factory.response.types.NotFound;

import javax.annotation.Nullable;

import static utils.common.factory.enums.ResponseTypes.INVALID_ID;
import static utils.common.factory.enums.ResponseTypes.NOT_ALLOWED;
import static utils.common.factory.enums.ResponseTypes.NOT_FOUND;

public class ResponseFactory {

    @Nullable
    public static ResponseModel createResponse(Enum responseType) {
        if (NOT_FOUND.equals(responseType)) {
            return new NotFound().createResponse();
        } else if (INVALID_ID.equals(responseType)) {
            return new InvalidId().createResponse();
        } else if (NOT_ALLOWED.equals(responseType)) {
            return new NotAllowed().createResponse();
        }
        throw new RuntimeException(responseType + " is unknown");
    }
}