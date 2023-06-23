package me.niallmurray.slipstream.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "season",
        "round",
        "DriverStandings"
})
@Generated("jsonschema2pojo")
public class StandingsList {

  @JsonProperty("season")
  public String season;
  @JsonProperty("round")
  public String round;
  @JsonProperty("DriverStandings")
  @Valid
  public List<DriverStanding> driverStandings;

  @Override
  public String toString() {
    return "StandingsList{" +
            "season='" + season + '\'' +
            ", round='" + round + '\'' +
            ", driverStandings=" + driverStandings +
            '}';
  }
}
