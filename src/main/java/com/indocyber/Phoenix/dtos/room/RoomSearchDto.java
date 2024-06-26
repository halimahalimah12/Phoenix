package com.indocyber.Phoenix.dtos.room;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomSearchDto {
    private final String room;
    private final String type;
    private final String status;
    private final Integer pageNumber;
    private final Integer pageSize;
}
