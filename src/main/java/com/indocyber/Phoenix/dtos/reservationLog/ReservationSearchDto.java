package com.indocyber.Phoenix.dtos.reservationLog;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReservationSearchDto {
    private final String roomNumber;
    private final String username;
    private final LocalDate date;
    private final Integer pageNumber;
    private final  Integer pageSize;
}
