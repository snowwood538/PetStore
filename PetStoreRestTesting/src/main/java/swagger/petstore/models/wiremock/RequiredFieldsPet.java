package swagger.petstore.models.wiremock;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import swagger.petstore.models.swagger.PetModel;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({"name", "photoUrls"})
public class RequiredFieldsPet extends PetModel {
    @JsonProperty("name")
    private String name;
    @JsonProperty("photoUrls")
    private List<String> photoUrls;
}
