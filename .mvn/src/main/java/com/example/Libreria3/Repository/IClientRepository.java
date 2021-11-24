package com.example.Libreria3.Repository;

import com.example.Libreria3.Entities.Book;
import com.example.Libreria3.Entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientRepository extends JpaRepository<Client, Integer> {

    @Modifying
    @Query("UPDATE Client c SET c.name = :name, c.lastName = :lastName, c.phoneNumber = :phoneNumber, c.dni = :dni WHERE c.id = :id")
    void update(@Param("id") Integer id, @Param("name") String name, @Param("lastName") String lastName, @Param("phoneNumber") String phoneNumber, @Param("dni") Long dni);

    @Modifying
    @Query("UPDATE Client c SET c.register = true WHERE c.id = :id")
    void register (@Param("id") Integer id);

    @Query("SELECT c FROM Client c WHERE c.register = true")
    List<Client> available();

    @Query("SELECT c FROM Client c WHERE c.register = false")
    List<Client> disabled();
}
