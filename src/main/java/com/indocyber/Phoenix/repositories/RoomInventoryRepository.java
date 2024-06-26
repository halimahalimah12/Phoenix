package com.indocyber.Phoenix.repositories;

import com.indocyber.Phoenix.models.RoomInventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomInventoryRepository extends JpaRepository<RoomInventory,Integer> {
    @Query("""
            SELECT ri
            FROM RoomInventory ri
            WHERE ri.room.number LIKE %:number%
            """)
    Page<RoomInventory> findAllFromRoom(Pageable pageable, @Param("number") String number);
}
