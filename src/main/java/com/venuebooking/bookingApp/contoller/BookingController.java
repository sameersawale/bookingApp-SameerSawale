package com.venuebooking.bookingApp.contoller;

import com.venuebooking.bookingApp.DTOs.ApiResponse;
import com.venuebooking.bookingApp.service.interfaces.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/bookings")
@Slf4j
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/{slotId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<String> createBooking(@PathVariable("slotId") int slotId){
        log.info("Create booking request for slotId: {}", slotId);
        return bookingService.createBooking(slotId);
    }

    @PutMapping("/{bookingId}/cancel")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ApiResponse<String> cancelBooking(@PathVariable("bookingId") int bookingId){
        log.info("Cancel booking request for bookingId: {}", bookingId);
        return bookingService.cancelBooking(bookingId);
    }

}
