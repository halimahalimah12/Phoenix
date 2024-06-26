package com.indocyber.Phoenix.services;

import com.indocyber.Phoenix.dtos.inventory.InventoryRowItemDto;
import com.indocyber.Phoenix.dtos.room.inventory.InventoryRowDto;
import com.indocyber.Phoenix.models.Inventory;
import com.indocyber.Phoenix.repositories.InventoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Page<InventoryRowItemDto> getAll(){
        int pageNumber = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(pageNumber,pageSize);

        Page<Inventory> inventoryPage = inventoryRepository.findAll(pageable);
        List<InventoryRowItemDto> dtos = inventoryPage.getContent()
                .stream().map(inventory -> InventoryRowItemDto.builder()
                        .name(inventory.getName())
                        .stock(inventory.getStock())
                        .description(inventory.getDescription())
                        .build())
                .toList();
        return new PageImpl<>(dtos,pageable,inventoryPage.getTotalElements());
    }


}
