package swagger.petstore.models;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    private long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    @JsonGetter
    public long getId() {
        return id;
    }
    @JsonSetter
    public void setId(long id) {
        this.id = id;
    }
    @JsonGetter
    public Category getCategory() {
        return category;
    }
    @JsonSetter
    public void setCategory(Category category) {
        this.category = category;
    }
    @JsonGetter
    public String getName() {
        return name;
    }
    @JsonSetter
    public void setName(String name) {
        this.name = name;
    }
    @JsonGetter
    public List<String> getPhotoUrls() {
        return photoUrls;
    }
    @JsonSetter
    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }
    @JsonGetter
    public List<Tag> getTags() {
        return tags;
    }
    @JsonSetter
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
    @JsonGetter
    public String getStatus() {
        return status;
    }
    @JsonSetter
    public void setStatus(String status) {
        this.status = status;
    }
}