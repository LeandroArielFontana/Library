package libreria2.Springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import libreria2.Springboot.Model.Entities.Publisher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IPublisherRepository extends JpaRepository<Publisher, Integer> {

    @Modifying
    @Query("UPDATE Publisher p SET p.name = :name WHERE p.id = :id ")
    void updateName(@Param("id") Integer id, @Param("name") String name);
    
}
