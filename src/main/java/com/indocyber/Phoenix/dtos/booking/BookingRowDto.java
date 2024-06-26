package com.indocyber.Phoenix.dtos.booking;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookingRowDto {
    private final String number;
    private final Integer floor;
    private final String type;
    private final Integer guestLimit;
    private final Double costPerDay;
    private final String status;

}
