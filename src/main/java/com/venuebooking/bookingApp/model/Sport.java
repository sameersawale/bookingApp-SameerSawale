package com.venuebooking.bookingApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "sport")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sport {

    @Id
    private int sportId;

    private String sportCode;

    private String sportName;


}
