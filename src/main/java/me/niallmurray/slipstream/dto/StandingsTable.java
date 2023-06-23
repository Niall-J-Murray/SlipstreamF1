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
        "StandingsLists"
})
@Generated("jsonschema2pojo")
public class StandingsTable {

  @JsonProperty("season")
  public String season;
  @JsonProperty("StandingsLists")
  @Valid
  public List<StandingsList> standingsLists;

  @Override
  public String toString() {
    return "StandingsTable{" +
            "season='" + season + '\'' +
            ", standingsLists=" + standingsLists +
            '}';
  }
}
