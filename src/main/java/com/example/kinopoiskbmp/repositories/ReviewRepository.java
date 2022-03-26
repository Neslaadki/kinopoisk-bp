package com.example.kinopoiskbmp.repositories;

import com.example.kinopoiskbmp.model.Review;
import com.example.kinopoiskbmp.model.ReviewKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewKey> {
    @Query("from Review r where r.reviewKey.content.id = :id" )
    List<Review> getReviewByContent(@Param(value = "id") long id);

    @Query("from Review r where r.reviewKey.client.id = :id" )
    List<Review> getReviewByClient(@Param(value = "id") long id);

    @Query("from Review r where r.reviewKey.client.id = :clientId and r.reviewKey.content.id = :contentId" )
    Review getReviewByClientAndContent(@Param(value = "clientId") long clientId,@Param(value = "contentId") long contentId);
}
