package swagger.petstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    @JsonProperty
    private long id;
    @JsonProperty
    private Category category;
    @JsonProperty
    private String name;
    @JsonProperty
    private List<String> photoUrls;
    @JsonProperty
    private List<Tag> tags;
    @JsonProperty
    private String status;
}