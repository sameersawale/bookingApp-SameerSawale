package com.venuebooking.bookingApp.model;

import com.venuebooking.bookingApp.constant.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue
    private int bookingId;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "slotId", nullable = false)
    private Slot slot;

    private LocalDateTime bookedAt;


}
