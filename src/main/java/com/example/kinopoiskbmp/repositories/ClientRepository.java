package com.example.kinopoiskbmp.repositories;

import com.example.kinopoiskbmp.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>{
    Client findByEmail(String email);
}
