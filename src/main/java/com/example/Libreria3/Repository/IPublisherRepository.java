package com.example.Libreria3.Repository;

import com.example.Libreria3.Entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPublisherRepository extends JpaRepository<Publisher, Integer> {

    @Modifying
    @Query("UPDATE Publisher p SET p.name = :name WHERE p.id = :id ")
    void updateName(@Param("id") Integer id, @Param("name") String name);

    @Modifying
    @Query("UPDATE Publisher p SET p.register = true WHERE p.id = :id")
    void register (@Param("id") Integer id);

    @Query("SELECT p FROM Publisher p WHERE p.register = true")
    List<Publisher> available();
}
