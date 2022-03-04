package com.example.kinopoiskbmp.repositories;

import com.example.kinopoiskbmp.entities.Review;
import com.example.kinopoiskbmp.entities.ReviewKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, ReviewKey> {
}
