package com.venuebooking.bookingApp.DTOs;

import com.venuebooking.bookingApp.model.Venue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueResponse {

    private int venueId;

    private String venueName;

    private String location;

    private String pinCode;

    private String sportName;

    public static VenueResponse from(Venue venue){
        return new VenueResponse(
                venue.getVenueId(),
                venue.getArena_name(),
                venue.getLocation(),
                venue.getPinCode(),
                venue.getSport().getSportName()
        );
    }
}
