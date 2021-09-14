package swagger.petstore.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    private int id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tags tags;
    private String status;
}
