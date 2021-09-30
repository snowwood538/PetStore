package com.api.petstore.models.pet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name"})
public class Tag {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
}