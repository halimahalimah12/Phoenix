package com.indocyber.Phoenix.repositories;

import com.indocyber.Phoenix.models.Room;
import com.indocyber.Phoenix.models.RoomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoomServiceRepository extends JpaRepository<RoomService,String> {
    @Query("""
            SELECT rs
            FROM RoomService rs
            WHERE (:number IS NULL OR rs.employeeNumber LIKE %:number%)
            AND (:name IS NULL OR rs.firstName LIKE %:name%
                OR rs.middleName LIKE %:name%
                OR rs.lastName LIKE %:name%
                OR CONCAT(rs.firstName,' ',rs.middleName,' ',rs.lastName) LIKE %:name% )
            """)
    Page<RoomService> findAllBySearch(Pageable pageable,
                               @Param("number") String number,
                               @Param("name") String name);
}
