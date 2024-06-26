package com.indocyber.Phoenix.dtos.booking;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class BookingUpsertResponseDto {
    private final String roomNumber;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final Double cost;
    private final String paymentMethod;
    private final String remark;


}
