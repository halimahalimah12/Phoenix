package com.indocyber.Phoenix.dtos.room;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomInfoDto {
    private final String number;
    private final Integer floor;
    private final String roomType;
    private final Integer guestLimit;
}
