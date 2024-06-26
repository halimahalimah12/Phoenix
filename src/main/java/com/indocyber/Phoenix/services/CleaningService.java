package com.indocyber.Phoenix.services;

import com.indocyber.Phoenix.dtos.room.RoomRowDto;
import com.indocyber.Phoenix.dtos.room.RoomSearchDto;
import com.indocyber.Phoenix.dtos.roomService.RoomServiceRosterDto;
import com.indocyber.Phoenix.dtos.roomService.RoomServiceRosterUpsertDto;
import com.indocyber.Phoenix.dtos.roomService.RoomServiceRowDto;
import com.indocyber.Phoenix.dtos.roomService.RoomServiceSearchDto;
import com.indocyber.Phoenix.models.Room;
import com.indocyber.Phoenix.models.RoomService;
import com.indocyber.Phoenix.repositories.RoomServiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CleaningService {
    private final RoomServiceRepository roomServiceRepository;

    public CleaningService(RoomServiceRepository roomServiceRepository) {
        this.roomServiceRepository = roomServiceRepository;
    }

    public Page<RoomServiceRowDto> getAll(RoomServiceSearchDto dto) {
        String number = dto.getEmployeeNumber() == null || dto.getEmployeeNumber().isBlank() ? null: dto.getEmployeeNumber();
        String name = dto.getEmployeeNumber() == null || dto.getEmployeeNumber().isBlank() ? null : dto.getEmployeeNumber();

        int pageNumber = dto.getPageNumber() == null ? 0 : dto.getPageNumber();
        int pageSize = dto.getPageSize() == null ? 10 : dto.getPageSize();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<RoomService> roomServicePage = roomServiceRepository.findAllBySearch(pageable,number,name);
        List<RoomServiceRowDto> dtos = roomServicePage.getContent()
                .stream().map(roomService -> RoomServiceRowDto.builder()
                        .number(roomService.getEmployeeNumber())
                        .fullName(roomService.getFirstName()+ ' ' + roomService.getMiddleName() + ' ' + roomService.getLastName())
                        .company(roomService.getOutsourchingCompany())
                        .build())
                .toList();
        return new PageImpl<>(dtos,pageable,roomServicePage.getTotalElements());
    }

    public RoomServiceRosterUpsertDto getForUpdate(String number){
        RoomService roomService = findRoomService(number);
        return RoomServiceRosterUpsertDto.builder()
                .employeeNumber(roomService.getEmployeeNumber())
                .firstName(roomService.getFirstName())
                .middleName(roomService.getMiddleName())
                .lastName(roomService.getLastName())
                .company(roomService.getOutsourchingCompany())
                .mondayRosterStart(roomService.getMondayRosterStart())
                .mondayRosterFinish(roomService.getMondayRosterFinish())
                .tuesdayRosterStart(roomService.getTuesdayRosterStart())
                .tuesdayRosterFinish(roomService.getTuesdayRosterFinish())
                .wednesdayRosterStart(roomService.getWednesdayRosterStart())
                .wednesdayRosterFinish(roomService.getWednesdayRosterFinish())
                .thursdayRosterStart(roomService.getThursdayRosterStart())
                .thursdayRosterFinish(roomService.getThursdayRosterFinish())
                .saturdayRosterStart(roomService.getSaturdayRosterStart())
                .saturdayRosterFinish(roomService.getSaturdayRosterFinish())
                .sundayRosterStart(roomService.getSundayRosterStart())
                .sundayRosterFinish(roomService.getSundayRosterFinish())
                .build();

    }

    private RoomService findRoomService(String number) {
        return roomServiceRepository.findById(number)
                .orElseThrow(()->new IllegalArgumentException("not found Id"));
    }

    public RoomServiceRosterDto getRoomServiceByNumber(String number){
        RoomService roomService = findRoomService(number);
        return  RoomServiceRosterDto.builder()
                .employeeNumber(roomService.getEmployeeNumber())
                .fullName(roomService.getFirstName()+' '+roomService.getMiddleName()+' '+roomService.getLastName())
                .company(roomService.getOutsourchingCompany())
                .mondayRosterStart(roomService.getMondayRosterStart())
                .mondayRosterFinish(roomService.getMondayRosterFinish())
                .tuesdayRosterStart(roomService.getTuesdayRosterStart())
                .tuesdayRosterFinish(roomService.getTuesdayRosterFinish())
                .wednesdayRosterStart(roomService.getWednesdayRosterStart())
                .wednesdayRosterFinish(roomService.getWednesdayRosterFinish())
                .thursdayRosterStart(roomService.getThursdayRosterStart())
                .thursdayRosterFinish(roomService.getThursdayRosterFinish())
                .saturdayRosterStart(roomService.getSaturdayRosterStart())
                .saturdayRosterFinish(roomService.getSaturdayRosterFinish())
                .sundayRosterStart(roomService.getSundayRosterStart())
                .sundayRosterFinish(roomService.getSundayRosterFinish())
                .build();
    }

    public void save(RoomServiceRosterUpsertDto dto) {

        RoomService roomServiceSave = RoomService.builder()
                .employeeNumber(dto.getEmployeeNumber())
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .outsourchingCompany(dto.getCompany())
                .mondayRosterStart(dto.getMondayRosterStart())
                .mondayRosterFinish(dto.getMondayRosterFinish())
                .tuesdayRosterStart(dto.getTuesdayRosterStart())
                .tuesdayRosterFinish(dto.getTuesdayRosterFinish())
                .wednesdayRosterStart(dto.getWednesdayRosterStart())
                .wednesdayRosterFinish(dto.getWednesdayRosterFinish())
                .thursdayRosterStart(dto.getThursdayRosterStart())
                .thursdayRosterFinish(dto.getThursdayRosterFinish())
                .fridayRosterStart(dto.getFridayRosterStart())
                .fridayRosterFinish(dto.getFridayRosterFinish())
                .saturdayRosterStart(dto.getSaturdayRosterStart())
                .saturdayRosterFinish(dto.getSaturdayRosterFinish())
                .sundayRosterStart(dto.getSundayRosterStart())
                .sundayRosterFinish(dto.getSundayRosterFinish())
                .build();
        roomServiceRepository.save(roomServiceSave);
    }
}
