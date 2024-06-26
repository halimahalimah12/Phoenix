package com.indocyber.Phoenix.dtos.booking;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingDetailDto {
    private final String number;
    private final Integer floor;
    private final String type;
    private final Integer guestLimit;
    private final Double costPerDay;
    private final String description;
}
