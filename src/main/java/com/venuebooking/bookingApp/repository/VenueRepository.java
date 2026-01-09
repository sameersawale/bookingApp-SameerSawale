package com.venuebooking.bookingApp.repository;

import com.venuebooking.bookingApp.model.Slot;
import com.venuebooking.bookingApp.model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Integer> {


    List<Venue> findBySportSportId(int sportId);
}
