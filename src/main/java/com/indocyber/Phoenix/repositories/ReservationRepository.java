package com.indocyber.Phoenix.repositories;

import com.indocyber.Phoenix.models.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,String> {

    @Query("""
            SELECT res
            FROM Reservation res
            WHERE (:roomNumber IS NULL OR res.room.number LIKE %:roomNumber%)
            AND (:username IS NULL OR res.guest.username LIKE %:username%)
            AND (:date IS NULL OR res.checkIn < :date )
            """)
    Page<Reservation> findAllSearch(Pageable pageable,
                                    @Param("roomNumber") String roomNumber,
                                    @Param("username") String username,
                                    @Param("date") LocalDate date);

    @Query("""
            SELECT res
            FROM Reservation res
            WHERE res.room.number LIKE %:number%
            """)
    Boolean findRoom(@Param("number") String number);

    @Query("""
            SELECT res
            FROM Reservation res
            WHERE res.guest.username LIKE %:username%
            """)
    List<Reservation> findByUsername(@Param("username") String username);
}
