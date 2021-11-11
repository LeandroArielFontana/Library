package com.example.Libreria3.Repository;

import com.example.Libreria3.Entities.Borrowed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBorrowedRepository extends JpaRepository  <Borrowed, Integer>{

    @Modifying
    @Query("UPDATE Borrowed b SET b.register = true WHERE b.id = :id")
    void register (@Param("id") Integer id);
}
