package com.venuebooking.bookingApp.service.interfaces;

import com.venuebooking.bookingApp.DTOs.ApiResponse;
import com.venuebooking.bookingApp.DTOs.AvailabilityDto;
import com.venuebooking.bookingApp.DTOs.VenueResponse;

import java.util.List;

public interface AvailabilityService {

    public ApiResponse<List<VenueResponse>> findAvailableVenues(AvailabilityDto availabilityDto);
}
