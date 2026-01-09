package com.venuebooking.bookingApp.service.interfaces;

import com.venuebooking.bookingApp.DTOs.ApiResponse;

public interface BookingService {

    public ApiResponse<String> createBooking(int slotId);

    public ApiResponse<String> cancelBooking(int bookingId);
}
