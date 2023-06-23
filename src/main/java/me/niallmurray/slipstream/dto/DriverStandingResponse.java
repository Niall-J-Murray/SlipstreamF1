package me.niallmurray.slipstream.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "MRData"
})
@Generated("jsonschema2pojo")
public class DriverStandingResponse {

  @JsonProperty("MRData")
  @Valid
  public MRData mRData;

  @Override
  public String toString() {
    return "DriverStandingResponse{" +
            "mRData=" + mRData +
            '}';
  }
}
