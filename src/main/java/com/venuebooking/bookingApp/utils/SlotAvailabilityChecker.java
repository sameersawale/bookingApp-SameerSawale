package com.venuebooking.bookingApp.utils;

import com.venuebooking.bookingApp.model.Slot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
@Slf4j
public class SlotAvailabilityChecker {

    public boolean isSlotAvailable(List<Slot>slots, LocalTime requestedStartTime,
                                   LocalTime requestedEndTime){
        log.info("Checking slot availability between {} - {}", requestedStartTime, requestedEndTime);

        for (Slot slot:slots){
            if(isWithinRange(slot, requestedStartTime, requestedEndTime)){
                log.info("Available slot found: {} - {}", slot.getStartTime(), slot.getEndTime());
                return true;
            }
        }
        return false;
    }

    private boolean isWithinRange(Slot slot, LocalTime requestedStartTime, LocalTime requestedEndTime){
        return slot.isAvailable() && !slot.getStartTime().isBefore(requestedStartTime)
                && !slot.getEndTime().isAfter(requestedEndTime);
    }
}
