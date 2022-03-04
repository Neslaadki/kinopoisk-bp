package com.example.kinopoiskbmp.repositories;

import com.example.kinopoiskbmp.entities.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {
}
