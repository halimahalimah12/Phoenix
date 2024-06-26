package com.indocyber.Phoenix.dtos.room;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomUpsertRequestDto {
    @NotNull
    @NotBlank (message = "Please, Entry Number Room")
    private final String number;
    @NotNull(message = "Please, Entry Floor!")
    private final Integer floor;
    @NotBlank (message = "Please, Select Type Room")
    private final String roomType;
    @NotNull(message = "Please, Entry Guest Limit")
    private final Integer guestLimit;
    private final String description;
    @NotNull(message = "Please, Entry Cost")
    private final Double cost;
}
