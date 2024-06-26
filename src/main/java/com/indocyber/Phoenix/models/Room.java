package com.indocyber.Phoenix.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Rooms")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @Column(name = "Number")
    private String number;
    @Column(name = "Floor")
    private Integer floor;
    @Column(name = "RoomType")
    private String roomType;
    @Column(name = "GuestLimit")
    private Integer guestLimit;
    @Column(name = "Description")
    private String description;
    @Column(name = "Cost")
    private Double cost;

}
