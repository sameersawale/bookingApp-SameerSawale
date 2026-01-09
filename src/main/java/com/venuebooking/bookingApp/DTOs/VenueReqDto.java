package com.venuebooking.bookingApp.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NonNull;

@Data
public class VenueReqDto {

    @NotBlank(message = "Arena name must not be blank")
    private String arena_name;

    @NotBlank(message = "Location must not be blank")
    private String location;

    @NotBlank(message = "Pin code must not be blank")
    @Pattern(
            regexp = "^[0-9]{6}$",
            message = "Pin code must be exactly 6 digits"
    )
    private String pinCode;

    @NotNull(message = "Sport Id must not be null")
    private int sportId;
}
