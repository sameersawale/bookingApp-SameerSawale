package com.venuebooking.bookingApp.repository;

import com.venuebooking.bookingApp.model.Slot;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {

    List<Slot> findByVenueVenueId(int venueId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Slot>findBySlotId(int slotId);
}
