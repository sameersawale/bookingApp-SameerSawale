package com.venuebooking.bookingApp.service.interfaces;

import com.venuebooking.bookingApp.DTOs.ApiResponse;
import com.venuebooking.bookingApp.DTOs.VenueReqDto;
import com.venuebooking.bookingApp.DTOs.VenueResponse;
import com.venuebooking.bookingApp.model.Venue;

import java.util.List;

public interface VenueService {

    public ApiResponse<String> addVenue(VenueReqDto venueReqDto);

    public List<VenueResponse> getAllVenues();

    public VenueResponse getVenueByID(int venueId);

    public ApiResponse<String> deleteVenue(int venueId);
}
