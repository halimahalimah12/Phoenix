package com.indocyber.Phoenix.dtos.inventory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryRowItemDto {
    private final String name;
    private final Integer stock;
    private final String description;
}
