package com.indocyber.Phoenix.repositories;

import com.indocyber.Phoenix.models.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomRepository extends JpaRepository<Room,String> {

    @Query("""
            SELECT r
            FROM Room r
            WHERE (:room IS NULL OR r.number LIKE %:room%)
            AND (:type IS NULL OR r.roomType LIKE %:type%)
            """)
    Page<Room> findAll(Pageable pageable,
                       @Param("room") String room,
                       @Param("type") String type);
}
