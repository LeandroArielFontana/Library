package com.example.Libreria.Repository;

import com.example.Libreria.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository <Book, Integer>{

    @Modifying
    @Query("UPDATE Book b SET b.register = true WHERE b.id = :id")
    void register (@Param("id") Integer id);

    @Query("SELECT b FROM Book b WHERE b.register = true")
    List<Book> available();
}
