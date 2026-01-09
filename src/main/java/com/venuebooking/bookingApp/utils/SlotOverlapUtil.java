package com.venuebooking.bookingApp.utils;

import com.venuebooking.bookingApp.model.Slot;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class SlotOverlapUtil {
    public boolean hasOverlap(List<Slot> existingSlots, LocalTime newStart, LocalTime newEnd){
        for(Slot slot:existingSlots){
            if (newStart.isBefore(slot.getEndTime()) && newEnd.isAfter(slot.getStartTime())){
                return true;
            }
        }
        return false;
    }
}
