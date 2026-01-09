package com.venuebooking.bookingApp.service.interfaces;

import com.venuebooking.bookingApp.DTOs.ApiResponse;
import com.venuebooking.bookingApp.DTOs.SlotReqDto;
import com.venuebooking.bookingApp.model.Slot;

import java.util.List;

public interface SlotService {

    public ApiResponse<String> createSlot(int venueId, SlotReqDto slotReqDto);

}
