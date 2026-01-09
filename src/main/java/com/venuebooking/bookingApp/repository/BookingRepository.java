package com.venuebooking.bookingApp.repository;

import com.venuebooking.bookingApp.constant.BookingStatus;
import com.venuebooking.bookingApp.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    boolean existsBySlotSlotIdAndStatus(int slotId, BookingStatus status);
}
