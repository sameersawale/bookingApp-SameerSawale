package com.venuebooking.bookingApp.DTOs;

import com.venuebooking.bookingApp.http.HttpRequest;
import lombok.Data;

import java.util.List;

@Data
public class SportsApiResponse {

    private String status;

    private String message;

    private List<HttpRequest> data;
}
