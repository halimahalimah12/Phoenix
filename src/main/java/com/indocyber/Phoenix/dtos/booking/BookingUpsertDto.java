package com.indocyber.Phoenix.dtos.booking;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class BookingUpsertDto {
    private final String number;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final Double cost;
    private final String paymentMethod;
    private final String remark;
    private final String username;
    private final String reservationMethod;
}
