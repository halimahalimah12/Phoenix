package com.indocyber.Phoenix.dtos.reservationLog;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ReservationDetailDto {
    private final String code;
    private final String reservationMethod;
    private final String roomNumber;
    private final String roomFloor;
    private final String roomType;
    private final String guestUsername;
    private final String guestFullName;
    private final LocalDate bookDate;
    private final String paymentMethod;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final Double cost;
    private final LocalDate paymentDate;
    private final String remark;
}
