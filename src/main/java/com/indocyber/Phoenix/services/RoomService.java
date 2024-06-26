package com.indocyber.Phoenix.services;

import com.indocyber.Phoenix.dtos.SelectListInventory;
import com.indocyber.Phoenix.dtos.room.RoomInfoDto;
import com.indocyber.Phoenix.dtos.room.RoomRowDto;
import com.indocyber.Phoenix.dtos.room.RoomSearchDto;
import com.indocyber.Phoenix.dtos.room.RoomUpsertRequestDto;
import com.indocyber.Phoenix.dtos.room.inventory.InventoryRowDto;
import com.indocyber.Phoenix.models.Inventory;
import com.indocyber.Phoenix.models.Room;
import com.indocyber.Phoenix.models.RoomInventory;
import com.indocyber.Phoenix.repositories.InventoryRepository;
import com.indocyber.Phoenix.repositories.RoomInventoryRepository;
import com.indocyber.Phoenix.repositories.RoomRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final InventoryRepository inventoryRepository;
    private final RoomInventoryRepository roomInventoryRepository;


    public RoomService(RoomRepository roomRepository, InventoryRepository inventoryRepository, RoomInventoryRepository roomInventoryRepository) {
        this.roomRepository = roomRepository;
        this.inventoryRepository = inventoryRepository;
        this.roomInventoryRepository = roomInventoryRepository;
    }

    public Page<RoomRowDto> getAll(RoomSearchDto dto) {
        String room = dto.getRoom() == null || dto.getRoom().isBlank() ? null: dto.getRoom();
        String type = dto.getType() == null || dto.getType().isBlank() ? null : dto.getType();
        String status = dto.getStatus() == null ||dto.getStatus().isBlank() ? null : dto.getStatus();

        int pageNumber = dto.getPageNumber() == null ? 0 : dto.getPageNumber();
        int pageSize = dto.getPageSize() == null ? 10 : dto.getPageSize();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Room> roomPage = roomRepository.findAll(pageable,room,type);
        List<RoomRowDto> dtos = roomPage.getContent()
                .stream().map(room1 -> RoomRowDto.builder()
                        .number(room1.getNumber())
                        .floor(room1.getFloor())
                        .roomType(room1.getRoomType())
                        .guestLimit(room1.getGuestLimit())
                        .cost(room1.getCost())
                        .build())
                .toList();
        return new PageImpl<>(dtos,pageable,roomPage.getTotalElements());
    }

    public RoomUpsertRequestDto getRoomNumber(String number) {
        Room room = findRoomById(number);
        return convertRoomUpsertRequestDto(room);
    }

    public RoomInfoDto getRoomInfoByNumber(String number) {
        Room room = findRoomById(number);
        return RoomInfoDto.builder()
                .number(room.getNumber())
                .floor(room.getFloor())
                .roomType(room.getRoomType())
                .guestLimit(room.getGuestLimit())
                .build();
    }

    private RoomUpsertRequestDto convertRoomUpsertRequestDto(Room room) {
        return RoomUpsertRequestDto.builder()
                .number(room.getNumber())
                .floor(room.getFloor())
                .roomType(room.getRoomType())
                .guestLimit(room.getGuestLimit())
                .description(room.getDescription())
                .cost(room.getCost())
                .build();

    }

    public void save(RoomUpsertRequestDto dto){

        var room = Room.builder()
                .number(dto.getNumber())
                .floor(dto.getFloor())
                .roomType(dto.getRoomType())
                .guestLimit(dto.getGuestLimit())
                .description(dto.getDescription())
                .cost(dto.getCost())
                .build();
        roomRepository.save(room);
    }

    private Room findRoomById(String number) {
        return roomRepository.findById(number).orElseThrow(
                ()-> new IllegalArgumentException("Room not found")
        );
    }

   public Page<InventoryRowDto> findInventory(String number) {

       int pageNumber = 0 ;
       int pageSize = 10;
       Pageable pageable = PageRequest.of(pageNumber, pageSize);

       Page<RoomInventory> inventories = roomInventoryRepository.findAllFromRoom(pageable,number);
        List<InventoryRowDto> rowDtos = inventories.getContent().stream()
                .map(roomInventory -> InventoryRowDto.builder()
                        .id(roomInventory.getId())
                        .name(roomInventory.getInventory().getName())
                        .stock(roomInventory.getInventory().getStock())
                        .quantity(roomInventory.getQuantity())
                        .build())
                .toList();
        return new PageImpl<>(rowDtos,pageable,inventories.getTotalElements());
    }

    public List<SelectListInventory> listInventories(){
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventories.stream().map(
                inventory -> SelectListInventory.builder()
                        .value(inventory.getName())
                        .build())
                .toList();
    }
}
