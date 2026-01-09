package com.venuebooking.bookingApp.service.Impl;

import com.venuebooking.bookingApp.DTOs.ApiResponse;
import com.venuebooking.bookingApp.constant.BookingStatus;
import com.venuebooking.bookingApp.exception.ResourceNotFoundException;
import com.venuebooking.bookingApp.model.Booking;
import com.venuebooking.bookingApp.model.Slot;
import com.venuebooking.bookingApp.repository.BookingRepository;
import com.venuebooking.bookingApp.repository.SlotRepository;
import com.venuebooking.bookingApp.service.interfaces.BookingService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private BookingRepository bookingRepository;


    @Transactional
    public ApiResponse<String> createBooking(int slotId) {

        Slot slot=slotRepository.findBySlotId(slotId).orElseThrow(()->{
            log.error("Slot not found {}", slotId);
            return new IllegalArgumentException("Invalid slot selected.");
        });

        if (!slot.isAvailable()){
            log.error("Slot {} already booked", slot.getSlotId());
            return new ApiResponse<>(
                    false,
                    "slot is already booked",
                    null
            );
        }

//        Mark slot as booked (Immutable time)
        slot.setAvailable(false);

        Booking booking= Booking.builder()
                .slot(slot)
                .status(BookingStatus.ACTIVE)
                .bookedAt(LocalDateTime.now())
                .build();
        bookingRepository.save(booking);
        log.info("Booking successful for slotId: {}, bookingId: {}",
                slot.getSlotId(), booking.getBookingId());

        return new ApiResponse<>(
                true,
                "Booking created successfully",
                "Booking Id: "+booking.getBookingId()
        );
    }

    @Transactional
    public ApiResponse<String> cancelBooking(int bookingId) {

        log.info("Cancel booking request for bookingId: {}", bookingId);

        Booking booking=bookingRepository.findById(bookingId).orElseThrow(()->
            new ResourceNotFoundException("Booking not found."));

        if (booking.getStatus()==BookingStatus.CANCELLED){
            log.error("Booking {} already cancelled", bookingId);
            throw new IllegalArgumentException("Booking already cancelled");
        }

        Slot slot=booking.getSlot();

        booking.setStatus(BookingStatus.CANCELLED);
        slot.setAvailable(true);

        bookingRepository.save(booking);
        slotRepository.save(slot);

        log.info("Booking {} cancelled. Slot {} is now available", bookingId, slot.getSlotId());

        return new ApiResponse<>(
                true,
                "Booking cancelled successfully",
                "Booking id is cancelled"
        );
    }
}
