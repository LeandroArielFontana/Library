package com.example.Libreria.Repository;

import com.example.Libreria.Entities.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserSecurity, Integer> {

    Optional<UserSecurity> findByUsername(String username);
}
