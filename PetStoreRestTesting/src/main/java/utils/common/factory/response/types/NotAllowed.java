package utils.common.factory.response.types;

import com.api.petstore.models.response.ResponseModel;
import utils.common.factory.response.IResponse;

public class NotAllowed implements IResponse {

    @Override
    public ResponseModel createResponse() {
        ResponseModel responseModel = new ResponseModel();
        responseModel.setCode(405);
        responseModel.setType("Error");
        responseModel.setMessage("Method Not Allowed");
        return responseModel;
    }
}