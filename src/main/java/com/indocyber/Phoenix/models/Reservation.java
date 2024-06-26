package com.indocyber.Phoenix.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Reservations")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @Column(name = "Code")
    private String code;
    @Column(name = "ReservationMethod")
    @Enumerated(EnumType.STRING)
    private ReservationMethod reservationMethod;
    @ManyToOne
    @JoinColumn(name = "RoomNumber")
    private Room room;
    @ManyToOne
    @JoinColumn(name = "GuestUsername")
    private Guest guest;
    @Column(name = "BookDate")
    private LocalDate bookDate;
    @Column(name = "CheckIn")
    private LocalDate checkIn;
    @Column(name = "CheckOut")
    private LocalDate checkOut;
    @Column(name = "cost")
    private Double cost;
    @Column(name = "PaymentDate")
    private LocalDate paymentDate;
    @Column(name = "PaymentMethod")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Column(name = "Renmark")
    private String renmark;
}
