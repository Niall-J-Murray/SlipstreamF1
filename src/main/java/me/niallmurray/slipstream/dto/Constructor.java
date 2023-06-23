package me.niallmurray.slipstream.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "constructorId",
        "url",
        "name",
        "nationality"
})
@Generated("jsonschema2pojo")
public class Constructor {

  @JsonProperty("constructorId")
  public String constructorId;
  @JsonProperty("url")
  public String url;
  @JsonProperty("name")
  public String name;
  @JsonProperty("nationality")
  public String nationality;

  @Override
  public String toString() {
    return "Constructor{" +
            "constructorId='" + constructorId + '\'' +
            ", url='" + url + '\'' +
            ", name='" + name + '\'' +
            ", nationality='" + nationality + '\'' +
            '}';
  }
}
