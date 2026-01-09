package com.venuebooking.bookingApp.repository;

import com.venuebooking.bookingApp.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository <Sport, Integer>{
}
