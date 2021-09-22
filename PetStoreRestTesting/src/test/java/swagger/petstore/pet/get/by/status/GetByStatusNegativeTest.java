package swagger.petstore.pet.get.by.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.Assert;
import org.testng.annotations.Test;
import swagger.petstore.api.requests.PetRequests;
import swagger.petstore.api.requests.Specifications;
import swagger.petstore.data_providers.pet.PetDataProvider;
import swagger.petstore.models.PetModel;

import java.util.List;

import static swagger.petstore.api.instances.endpoints.PetEndpoints.PETS_BY_STATUS;
import static swagger.petstore.api.instances.queries.PetQueries.PET_STATUS;
import static swagger.petstore.api.instances.urls.BaseUrls.BASE_URL;

public class GetByStatusNegativeTest {

    @SneakyThrows
    @Test(dataProvider = "Incorrect_pet_statuses", dataProviderClass = PetDataProvider.class)
    public void getPetByStatusCorrectStatuses(String[] status) {
        RequestSpecification spec = Specifications.requestSpecification(BASE_URL, PETS_BY_STATUS);
        Response response = PetRequests.get(spec.queryParam(PET_STATUS, status[0]));
        JsonMapper mapper = new JsonMapper();
        List<PetModel> myObjects = mapper.readValue(response.asString(), new TypeReference<>(){});
        Assert.assertEquals(myObjects.size(), 0, "Pet statuses list is not empty");
    }
}