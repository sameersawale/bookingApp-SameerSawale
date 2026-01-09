package com.venuebooking.bookingApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "venue")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int venueId;

    private String arena_name;

    private String location;

    private String pinCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sportId", nullable = false)
    private Sport sport;


}
