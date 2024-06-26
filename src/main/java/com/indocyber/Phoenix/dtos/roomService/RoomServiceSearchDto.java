package com.indocyber.Phoenix.dtos.roomService;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomServiceSearchDto {
    private final String employeeNumber;
    private final String fullName;
    private final Integer pageNumber;
    private final Integer pageSize;
}
