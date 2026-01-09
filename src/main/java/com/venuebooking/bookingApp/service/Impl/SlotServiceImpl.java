package com.venuebooking.bookingApp.service.Impl;

import com.venuebooking.bookingApp.DTOs.ApiResponse;
import com.venuebooking.bookingApp.DTOs.SlotReqDto;
import com.venuebooking.bookingApp.exception.ResourceNotFoundException;
import com.venuebooking.bookingApp.model.Slot;
import com.venuebooking.bookingApp.model.Venue;
import com.venuebooking.bookingApp.repository.SlotRepository;
import com.venuebooking.bookingApp.repository.VenueRepository;
import com.venuebooking.bookingApp.service.interfaces.SlotService;
import com.venuebooking.bookingApp.utils.SlotOverlapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SlotServiceImpl implements SlotService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private VenueRepository venueRepository;

    @Autowired
    private SlotOverlapUtil slotOverlapUtil;

    @Override
    public ApiResponse<String> createSlot(int venueId, SlotReqDto slotReqDto) {

        if(!slotReqDto.getStartTime().isBefore(slotReqDto.getEndTime())){
            log.error("Invalid slot time range | startTime: {} , endTime: {}", slotReqDto.getStartTime(), slotReqDto.getEndTime());
            throw new IllegalArgumentException("Start time must be before end time");
        }

        //validate venue
        Venue venue=venueRepository.findById(venueId).orElseThrow(()->{
            log.error("Venue not found | venueId:{}", venueId);
            return new ResourceNotFoundException("Venue not found");
        });

        //Fetch existing slots
        List<Slot> existingSlots=slotRepository.findByVenueVenueId(venueId);

        //checking overlap
        if(slotOverlapUtil.hasOverlap(existingSlots, slotReqDto.getStartTime(), slotReqDto.getEndTime())){
            log.error("Slot overlap detected | venueId:{}, startTime:{}, endTime:{}", venueId, slotReqDto.getStartTime(), slotReqDto.getEndTime());
            throw new IllegalArgumentException("Slot overlap with existing slot!!");
        }

        Slot slot=Slot.builder()
                .startTime(slotReqDto.getStartTime())
                .endTime(slotReqDto.getEndTime())
                .venue(venue)
                .isAvailable(true)
                .build();
        slotRepository.save(slot);

        log.info("Slot created successfully | slotId:{}, venueId:{}", slot.getSlotId(), venueId);

        return ApiResponse.success("Slot added successfully");

    }


}
