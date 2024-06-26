package com.indocyber.Phoenix.services;

import com.indocyber.Phoenix.dtos.booking.*;
import com.indocyber.Phoenix.models.*;
import com.indocyber.Phoenix.repositories.GuestRepository;
import com.indocyber.Phoenix.repositories.ReservationRepository;
import com.indocyber.Phoenix.repositories.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class BookingService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    public BookingService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }
    public Page<BookingRowDto> getAll(BookingSearchDto dto){
        int pageNumber = dto.getPageNumber() == null ? 0 : dto.getPageNumber();
        int pageSize = dto.getPageSize() == null ? 10 : dto.getPageSize();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        String room = dto.getRoom()==null || dto.getRoom().isBlank() ? null : dto.getRoom();
        String type = dto.getType()==null || dto.getType().isBlank()?null: dto.getType();
        Page<Room> reservationPage = roomRepository.findAll(pageable,room,type);

        List<BookingRowDto> dtos = reservationPage.getContent().stream()
                .map(room1-> BookingRowDto.builder()
                        .number(room1.getNumber())
                        .floor(room1.getFloor())
                        .type(room1.getRoomType())
                        .guestLimit(room1.getGuestLimit())
                        .costPerDay(room1.getCost())
//                        .status("Booked")
                        .build())
                .toList();

        return new PageImpl<>(dtos,pageable,reservationPage.getTotalElements());


    }


    public BookingDetailDto getId(String id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("ID not found"));

        return BookingDetailDto.builder()
                .number(room.getNumber())
                .floor(room.getFloor())
                .type(room.getRoomType())
                .guestLimit(room.getGuestLimit())
                .costPerDay(room.getCost())
                .description(room.getDescription())
                .build();
    }

    public BookingUpsertResponseDto save(BookingUpsertDto dto) {
        String roomNumber = dto.getNumber();
        String tgl = LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yy",new Locale("id","ID")));
      //  Integer rool = 001;
        String code = roomNumber+'-'+tgl;


        Room room = roomRepository.findById(dto.getNumber()).orElseThrow(()->new IllegalArgumentException("id room not found"));

        PaymentMethod paymentMethod;
        if(dto.getPaymentMethod().equalsIgnoreCase("Credit Card")){
            paymentMethod = PaymentMethod.CA;
        } else if (dto.getPaymentMethod().equalsIgnoreCase("Direct Debit")) {
            paymentMethod=PaymentMethod.DD;
        }else if (dto.getPaymentMethod().equalsIgnoreCase("Voucher")){
            paymentMethod = PaymentMethod.VO;
        } else if (dto.getPaymentMethod().equalsIgnoreCase("Electronic Money")) {
            paymentMethod = PaymentMethod.EM;
        }else {
            paymentMethod = PaymentMethod.CA;
        }
        Guest guest = guestRepository.findUsername(dto.getUsername());

        ReservationMethod reservationMethod;
        if (dto.getReservationMethod().equalsIgnoreCase("Official Web")){
            reservationMethod = ReservationMethod.OW;
        } else if (dto.getReservationMethod().equalsIgnoreCase("On Site")) {
            reservationMethod = ReservationMethod.OS;
        } else {
            reservationMethod = ReservationMethod.AW;
        }

        Reservation reservation = Reservation.builder()
                .code(code)
                .room(room)
                .bookDate(LocalDate.now())
                .checkIn(dto.getCheckIn())
                .checkOut(dto.getCheckOut())
                .cost(dto.getCost())
                .guest(guest)
                .paymentDate(LocalDate.now())
                .paymentMethod(paymentMethod)
                .reservationMethod(reservationMethod)
                .renmark(dto.getRemark())
                .build();
        reservation = reservationRepository.save(reservation);

        return BookingUpsertResponseDto.builder()
                .roomNumber(reservation.getRoom().getNumber())
                .checkIn(reservation.getCheckIn())
                .checkOut(reservation.getCheckOut())
                .cost(reservation.getCost())
                .paymentMethod(reservation.getPaymentMethod().toString())
                .remark(reservation.getRenmark())
                .build();

    }

    public List<MyRoomDto> myRoom(String username) {
        List<Reservation> reservationList = reservationRepository.findByUsername(username);

        return reservationList.stream().map(reservation -> MyRoomDto.builder()
                .number(reservation.getRoom().getNumber())
                .floor(reservation.getRoom().getFloor())
                .roomType(reservation.getRoom().getRoomType())
                .guestLimit(reservation.getRoom().getGuestLimit())
                .costPerNight(reservation.getCost())
                .checkIn(reservation.getCheckIn())
                .checkOut(reservation.getCheckOut())
                .totalCost(ChronoUnit.DAYS.between(reservation.getCheckIn(),reservation.getCheckOut()) * reservation.getCost())
                .paymentDate(reservation.getPaymentDate())
                .paymentMethod(reservation.getPaymentMethod().getLabel())
                .remark(reservation.getRenmark())
                .build()
        ).toList();

    }
}
