package com.indocyber.Phoenix.services;

import com.indocyber.Phoenix.dtos.reservationLog.ReservationDetailDto;
import com.indocyber.Phoenix.dtos.reservationLog.ReservationRowDto;
import com.indocyber.Phoenix.dtos.reservationLog.ReservationSearchDto;
import com.indocyber.Phoenix.models.Reservation;
import com.indocyber.Phoenix.repositories.ReservationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Page<ReservationRowDto> getAll(ReservationSearchDto dto){
        String roomNumber = dto.getRoomNumber()== null || dto.getRoomNumber().isBlank() ? null : dto.getRoomNumber();
        String username = dto.getUsername() == null || dto.getUsername().isBlank() ? null : dto.getUsername();
        LocalDate date = dto.getDate()== null ?null : dto.getDate();

        int pageNumber = dto.getPageNumber() == null ? 0 : dto.getPageNumber();
        int pageSize = dto.getPageSize() == null ? 10 : dto.getPageSize();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Reservation> reservationPage = reservationRepository.findAllSearch(pageable,roomNumber,username,date);
        List<ReservationRowDto> dtos = reservationPage.getContent().stream()
                .map(reservation -> ReservationRowDto.builder()
                        .code(reservation.getCode())
                        .roomNumber(reservation.getRoom().getNumber())
                        .username(reservation.getGuest().getUsername())
                        .bookDate(reservation.getBookDate())
                        .checkIn(reservation.getCheckIn())
                        .checkOut(reservation.getCheckOut())
                        .paymentDate(reservation.getPaymentDate())
                        .build()
                ).toList();
        return new PageImpl<>(dtos, pageable, reservationPage.getTotalElements());
    }

    public ReservationDetailDto getId(String id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("ID not found"));

        return ReservationDetailDto.builder()
                .code(reservation.getCode())
//                .reservationMethod(reservation.getReservationMethod().toString())
                .roomNumber(reservation.getRoom().getNumber())
                .roomFloor(reservation.getRoom().getFloor().toString())
                .roomType(reservation.getRoom().getRoomType())
                .guestUsername(reservation.getGuest().getUsername())
                .guestFullName(reservation.getGuest().getFirstName()+' '+reservation.getGuest().getMiddleName()+' '+reservation.getGuest().getLastName())
                .bookDate(reservation.getBookDate())
//                .paymentMethod(reservation.getPaymentMethod().toString())
                .checkIn(reservation.getCheckIn())
                .checkOut(reservation.getCheckOut())
                .cost(reservation.getCost())
                .paymentDate(reservation.getPaymentDate())
                .remark(reservation.getRenmark())
                .build();

    }
}
