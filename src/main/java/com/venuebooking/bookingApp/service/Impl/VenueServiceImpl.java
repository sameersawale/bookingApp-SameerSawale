package com.venuebooking.bookingApp.service.Impl;

import com.venuebooking.bookingApp.DTOs.ApiResponse;
import com.venuebooking.bookingApp.DTOs.VenueReqDto;
import com.venuebooking.bookingApp.DTOs.VenueResponse;
import com.venuebooking.bookingApp.exception.ResourceNotFoundException;
import com.venuebooking.bookingApp.model.Sport;
import com.venuebooking.bookingApp.model.Venue;
import com.venuebooking.bookingApp.repository.SportRepository;
import com.venuebooking.bookingApp.repository.VenueRepository;
import com.venuebooking.bookingApp.service.interfaces.VenueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class VenueServiceImpl implements VenueService  {

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    SportRepository sportRepository;

    @Override
    public ApiResponse<String> addVenue(VenueReqDto venueReqDto) {

        Sport sport=sportRepository.findById(venueReqDto.getSportId()).orElseThrow(()->{
           log.error("Sport is not found. sportId:{}", venueReqDto.getSportId());
           return new ResourceNotFoundException("Sport not found");
        });


        Venue venue =Venue.builder()
                .arena_name(venueReqDto.getArena_name())
                .location(venueReqDto.getLocation())
                .pinCode(venueReqDto.getPinCode())
                .sport(sport)
                .build();

        venueRepository.save(venue);
        return ApiResponse.success("venue added successfully......");

    }

    @Override
    public List<VenueResponse> getAllVenues() {
        List<Venue> venues =venueRepository.findAll();

        List<VenueResponse>result=new ArrayList<>();

        for (Venue venue:venues){
          VenueResponse venueResponse=VenueResponse.from(venue);
          result.add(venueResponse);
        }
        return result;
    }

    @Override
    public VenueResponse getVenueByID(int venueId) {
        Venue venue=venueRepository.findById(venueId).orElseThrow(()->{
           log.error("Venue not found. venueId:{}", venueId);
           return new ResourceNotFoundException("Venue not found");
        });

        return VenueResponse.from(venue);
    }

    @Override
    public ApiResponse<String> deleteVenue(int venueId) {
        Venue venue=venueRepository.findById(venueId).orElseThrow(()->{
            log.error("Venue not found. venueId:{}", venueId);
            return new ResourceNotFoundException("Venue not found");
        });
        venueRepository.delete(venue);
        return new ApiResponse<>(
                true,
                "Venue deleted successfully",
                null
        );
    }
}
