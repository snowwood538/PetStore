package utils.common.factory.response.types;

import com.api.petstore.models.response.ResponseModel;
import utils.common.factory.response.IResponse;

public class NotFound implements IResponse {

    @Override
    public ResponseModel createResponse() {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(404);
        responseModel.setType("Not found");
        responseModel.setMessage("Pet not found");
        return responseModel;
    }
}