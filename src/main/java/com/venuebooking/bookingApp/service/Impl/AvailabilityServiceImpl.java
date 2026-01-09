package com.venuebooking.bookingApp.service.Impl;

import com.venuebooking.bookingApp.DTOs.ApiResponse;
import com.venuebooking.bookingApp.DTOs.AvailabilityDto;
import com.venuebooking.bookingApp.DTOs.VenueResponse;
import com.venuebooking.bookingApp.exception.ResourceNotFoundException;
import com.venuebooking.bookingApp.model.Slot;
import com.venuebooking.bookingApp.model.Venue;
import com.venuebooking.bookingApp.repository.SlotRepository;
import com.venuebooking.bookingApp.repository.SportRepository;
import com.venuebooking.bookingApp.repository.VenueRepository;
import com.venuebooking.bookingApp.service.interfaces.AvailabilityService;
import com.venuebooking.bookingApp.utils.SlotAvailabilityChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class AvailabilityServiceImpl implements AvailabilityService {

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private SlotAvailabilityChecker slotAvailabilityChecker;

    @Autowired
    SportRepository sportRepository;


    @Override
    public ApiResponse<List<VenueResponse>> findAvailableVenues(AvailabilityDto availabilityDto) {

        // Validate time range
        if (!availabilityDto.getStartTime().isBefore(availabilityDto.getEndTime())) {
            log.warn("Invalid time range | startTime={}, endTime={}",
                    availabilityDto.getStartTime(),
                    availabilityDto.getEndTime());
            throw new IllegalArgumentException("Start time must be before end time");
        }

//        Validate Sport
        if (!sportRepository.existsById(availabilityDto.getSportId())) {
            log.error("Invalid sportId {}", availabilityDto.getSportId());

            throw new ResourceNotFoundException("Invalid sport selected");
        }

//        Fetch Venues
        List<Venue> venues =
                venueRepository.findBySportSportId(availabilityDto.getSportId());

        if (venues.isEmpty()) {
            log.info("No venues found for sportId={}", availabilityDto.getSportId());

            throw new ResourceNotFoundException("No venues available for selected sport");
        }

//        Check slot availability
        List<VenueResponse> availableVenues = new ArrayList<>();

        for (Venue venue : venues) {
            List<Slot> freeSlots =
                    slotRepository.findByVenueVenueId(venue.getVenueId());

            if (slotAvailabilityChecker.isSlotAvailable(freeSlots, availabilityDto.getStartTime(), availabilityDto.getEndTime())) {
                availableVenues.add(VenueResponse.from(venue));
                log.debug("Venue {} available", venue.getVenueId());
            }
        }

//        No available slots
        if (availableVenues.isEmpty()) {
            log.info("No venues available for time range {} - {}", availabilityDto.getStartTime(), availabilityDto.getEndTime());

            throw new ResourceNotFoundException("No venue available for given time range");
        }

//        Success
        log.info("Found {} available venues", availableVenues.size());
        return new ApiResponse<>(
                true,
                "Available venues fetched successfully",
                availableVenues
        );

    }
}
