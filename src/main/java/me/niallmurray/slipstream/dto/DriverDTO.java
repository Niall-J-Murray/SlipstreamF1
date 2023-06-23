package me.niallmurray.slipstream.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "driverId",
        "permanentNumber",
        "code",
        "url",
        "givenName",
        "familyName",
        "dateOfBirth",
        "nationality",
        "constructor"
})
@Generated("jsonschema2pojo")
@Getter
@Setter
@NoArgsConstructor
public class DriverDTO {

  @JsonProperty("driverId")
  public String name;
  @JsonProperty("permanentNumber")
  public String carNumber;
  @JsonProperty("code")
  public String shortName;
  @JsonProperty("url")
  public String wikiPage;
  @JsonProperty("givenName")
  public String firstName;
  @JsonProperty("familyName")
  public String surname;
  @JsonProperty("dateOfBirth")
  public String dateOfBirth;
  @JsonProperty("nationality")
  public String nationality;
  @JsonProperty("constructor")
  public String constructor;

  @Override
  public String toString() {
    return "Driver{" +
            "driverId='" + name + '\'' +
            ", permanentNumber='" + carNumber + '\'' +
            ", code='" + shortName + '\'' +
            ", url='" + wikiPage + '\'' +
            ", givenName='" + firstName + '\'' +
            ", familyName='" + surname + '\'' +
            ", dateOfBirth='" + dateOfBirth + '\'' +
            ", nationality='" + nationality + '\'' +
            '}';
  }
}
