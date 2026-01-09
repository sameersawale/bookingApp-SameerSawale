package com.venuebooking.bookingApp.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HttpRequest {

    @JsonProperty("sport_id")
    private int sportId;

    @JsonProperty("sport_code")
    private String sportCode;

    @JsonProperty("sport_name")
    private String sportName;
}
