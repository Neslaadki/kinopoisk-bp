package com.example.kinopoiskbmp.repositories;

import com.example.kinopoiskbmp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
