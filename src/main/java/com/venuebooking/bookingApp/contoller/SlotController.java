package com.venuebooking.bookingApp.contoller;

import com.venuebooking.bookingApp.DTOs.ApiResponse;
import com.venuebooking.bookingApp.DTOs.SlotReqDto;
import com.venuebooking.bookingApp.service.interfaces.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/venues")
public class SlotController {

    @Autowired
    private SlotService slotService;

    @PostMapping("/{venueId}/slots")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<String> CreateSlot(@PathVariable("venueId")int venueId, @RequestBody SlotReqDto slotReqDto){
        return slotService.createSlot(venueId,
                slotReqDto);
    }
}
