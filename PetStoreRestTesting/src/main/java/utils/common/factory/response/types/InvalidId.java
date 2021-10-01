package utils.common.factory.response.types;

import com.api.petstore.models.response.ResponseModel;
import utils.common.factory.response.IResponse;

public class InvalidId implements IResponse {

    @Override
    public ResponseModel createResponse() {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(400);
        responseModel.setType("Error");
        responseModel.setMessage("Bad Request");
        return responseModel;
    }
}