package com.indocyber.Phoenix.dtos.booking;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class MyRoomDto {
    private final String number;
    private final Integer floor;
    private final String roomType;
    private final Integer guestLimit;
    private final Double costPerNight;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final Double totalCost;
    private final LocalDate paymentDate;
    private final String paymentMethod;
    private final String remark;
}
