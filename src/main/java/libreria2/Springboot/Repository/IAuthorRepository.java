package libreria2.Springboot.Repository;

import libreria2.Springboot.Model.Entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Integer>{
    
    @Modifying
    @Query("UPDATE Author a SET a.name = :name WHERE a.id = :id")
    void updateName(@Param("id") Integer id, @Param("name") String name);
    
}
