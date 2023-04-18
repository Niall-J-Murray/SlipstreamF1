package me.niallmurray.slipstream.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "xmlns",
        "series",
        "url",
        "limit",
        "offset",
        "total",
        "StandingsTable"
})
@Generated("jsonschema2pojo")
public class MRData {

  @JsonProperty("xmlns")
  public String xmlns;
  @JsonProperty("series")
  public String series;
  @JsonProperty("url")
  public String url;
  @JsonProperty("limit")
  public String limit;
  @JsonProperty("offset")
  public String offset;
  @JsonProperty("total")
  public String total;
  @JsonProperty("StandingsTable")
  @Valid
  public StandingsTable standingsTable;

  @Override
  public String toString() {
    return "MRData{" +
            "xmlns='" + xmlns + '\'' +
            ", series='" + series + '\'' +
            ", url='" + url + '\'' +
            ", limit='" + limit + '\'' +
            ", offset='" + offset + '\'' +
            ", total='" + total + '\'' +
            ", standingsTable=" + standingsTable +
            '}';
  }
}
