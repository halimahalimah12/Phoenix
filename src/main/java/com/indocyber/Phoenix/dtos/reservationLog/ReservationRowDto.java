package com.indocyber.Phoenix.dtos.reservationLog;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class ReservationRowDto {
    private final String code;
    private final String roomNumber;
    private final String username;
    private final LocalDate bookDate;
    private final  LocalDate checkIn;
    private final LocalDate checkOut;
    private final LocalDate paymentDate;
}
