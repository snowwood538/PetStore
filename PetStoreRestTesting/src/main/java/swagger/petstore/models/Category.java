package swagger.petstore.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Category {
    private int id;
    private String name;

    @JsonGetter
    public int getId() {
        return this.id;
    }
    @JsonGetter
    public String getName() {
        return this.name;
    }
    @JsonSetter
    public void setId(int id) {
        this.id = id;
    }
    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }
}
