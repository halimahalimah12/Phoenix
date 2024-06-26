package com.indocyber.Phoenix.dtos.room.inventory;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryRowDto {
    private final Integer id;
    private final String name;
    private final Integer stock;
    private final Integer quantity;
}
