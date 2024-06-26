package com.indocyber.Phoenix.repositories;

import com.indocyber.Phoenix.models.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdministratorRepository extends JpaRepository<Administrator,String> {

    @Query("""
            SELECT a
            FROM Administrator a
            WHERE a.username LIKE %:username%
            """)
    Administrator findUsername (@Param("username") String username);
}
