package com.indocyber.Phoenix.dtos.room;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoomRowDto {
    private final String number;
    private final Integer floor;
    private final String roomType;
    private final Integer guestLimit;
    private final Double cost;
    private final String status;

}
