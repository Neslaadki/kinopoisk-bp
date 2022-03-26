package com.example.kinopoiskbmp.services;

import com.example.kinopoiskbmp.dto.ReviewDTO;
import com.example.kinopoiskbmp.dto.ReviewIncomingDTO;
import com.example.kinopoiskbmp.model.Review;

import java.util.List;

public interface ReviewService {

    void saveReview(ReviewIncomingDTO r);

    List<ReviewDTO> getReviewByContent(Long contentId);

    List<ReviewDTO> getReviewByClient(Long clientId);

    ReviewDTO getReviewByClientAndContent(Long clientId, Long contentId);
}
