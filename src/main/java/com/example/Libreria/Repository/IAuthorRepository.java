package com.example.Libreria.Repository;

import com.example.Libreria.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Integer> {

    @Modifying
    @Query("UPDATE Author a SET a.name = :name WHERE a.id = :id")
    void updateName(@Param("id") Integer id, @Param("name") String name);

    @Modifying
    @Query("UPDATE Author a SET a.register = true WHERE a.id = :id")
    void register(@Param("id") Integer id);

    @Query("SELECT a FROM Author a WHERE a.register = true")
    List<Author> available();
}
