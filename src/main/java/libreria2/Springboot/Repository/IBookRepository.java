package libreria2.Springboot.Repository;

import libreria2.Springboot.Model.Entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository <Book, Integer>{
    
//    @Modifying
//    @Query("UPDATE Book b SET b.tittle = :tittle WHERE b.isbn = :isbn")
//    void updateTittle(@Param("isbn") Long isbn, @Param("tittle") String tittle);
//    
//    @Modifying
//    @Query("UPDATE Book b SET b.copies = :copies WHERE b.isbn = :isbn")
//    void updateCopies(@Param("isbn") Long isbn, @Param("copies") Integer copies);
}