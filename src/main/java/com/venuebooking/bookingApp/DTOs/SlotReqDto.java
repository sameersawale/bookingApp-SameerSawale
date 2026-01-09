package com.venuebooking.bookingApp.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
public class SlotReqDto {

    @NotNull(message = "Start time must not be null")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;

    @NotNull(message = "End time must not be null")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime endTime;

}
