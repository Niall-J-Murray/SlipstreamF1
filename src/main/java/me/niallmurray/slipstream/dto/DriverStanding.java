package me.niallmurray.slipstream.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "position",
        "positionText",
        "points",
        "wins",
        "Driver",
        "Constructors"
})
@Generated("jsonschema2pojo")
public class DriverStanding {

  @JsonProperty("position")
  public String position;
  @JsonProperty("positionText")
  public String positionText;
  @JsonProperty("points")
  public String points;
  @JsonProperty("wins")
  public String wins;
  @JsonProperty("Driver")
  @Valid
  public DriverDTO driverDTO;
  @JsonProperty("Constructors")
  @Valid
  public List<Constructor> constructors;

  @Override
  public String toString() {
    return "DriverStanding{" +
            "position='" + position + '\'' +
            ", positionText='" + positionText + '\'' +
            ", points='" + points + '\'' +
            ", wins='" + wins + '\'' +
            ", driver=" + driverDTO +
            ", constructors=" + constructors +
            '}';
  }
}
