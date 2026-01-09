package com.venuebooking.bookingApp.contoller;

import com.venuebooking.bookingApp.DTOs.ApiResponse;
import com.venuebooking.bookingApp.DTOs.AvailabilityDto;
import com.venuebooking.bookingApp.DTOs.VenueReqDto;
import com.venuebooking.bookingApp.DTOs.VenueResponse;
import com.venuebooking.bookingApp.exception.BadRequestException;
import com.venuebooking.bookingApp.model.Venue;
import com.venuebooking.bookingApp.service.Impl.AvailabilityServiceImpl;
import com.venuebooking.bookingApp.service.interfaces.VenueService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/venues")
@Slf4j
public class VenueController {

    @Autowired
    private VenueService venueService;

    @Autowired
    AvailabilityServiceImpl availabilityService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<String> addVenue(@Valid @RequestBody VenueReqDto venueReqDto){
        return venueService.addVenue(venueReqDto);
    }

    @GetMapping("/venues")
    @ResponseStatus(HttpStatus.FOUND)
    public List<VenueResponse> getAllVenues(){
        return venueService.getAllVenues();
    }

    @GetMapping("/{venueId}")
    @ResponseStatus(HttpStatus.FOUND)
    public VenueResponse getVenueById(@PathVariable("venueId") int venueId){
        return venueService.getVenueByID(venueId);
    }

    @DeleteMapping("/{venueId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<String> deleteVenue(@PathVariable("venueId") int venueId){
        return venueService.deleteVenue(venueId);
    }

    @GetMapping("/available")
    @ResponseStatus(HttpStatus.FOUND)
    public ApiResponse<List<VenueResponse>> getAvailableVenues(@RequestBody AvailabilityDto availabilityDto){
        if(availabilityDto.getStartTime().compareTo(availabilityDto.getEndTime())>=0){
            log.error("Invalid time range: {} - {}", availabilityDto.getStartTime(), availabilityDto.getEndTime());
            throw new BadRequestException("startTime must be before endTime");
        }
        return availabilityService.findAvailableVenues(availabilityDto);
    }


}
