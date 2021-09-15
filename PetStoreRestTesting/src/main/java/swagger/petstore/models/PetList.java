package swagger.petstore.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonFormat(shape=JsonFormat.Shape.ARRAY)
public class PetList {
    @JsonProperty
    private List<Pet> listWithPets;
}
