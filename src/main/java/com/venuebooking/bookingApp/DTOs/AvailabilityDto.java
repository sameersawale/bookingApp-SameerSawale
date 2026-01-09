package com.venuebooking.bookingApp.DTOs;

import lombok.Data;

import java.time.LocalTime;

@Data
public class AvailabilityDto {

    private int sportId;

    private LocalTime startTime;

    private LocalTime endTime;
}
