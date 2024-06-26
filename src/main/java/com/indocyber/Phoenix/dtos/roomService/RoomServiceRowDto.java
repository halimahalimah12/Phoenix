package com.indocyber.Phoenix.dtos.roomService;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoomServiceRowDto {
    private final String number;
    private final String  fullName;
    private final String company;
}
