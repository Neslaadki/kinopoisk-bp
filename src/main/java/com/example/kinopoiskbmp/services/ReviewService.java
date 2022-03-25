package com.example.kinopoiskbmp.services;

import com.example.kinopoiskbmp.dto.RequestReview;
import com.example.kinopoiskbmp.model.Review;
import com.example.kinopoiskbmp.model.ReviewKey;

import java.util.List;

public interface ReviewService {

    ReviewKey sendReview(RequestReview r);

    List<Review> getReviewByContent(Long contentId);

    List<Review> getReviewByClient(Long clientId);

    Review getReviewByClientAndContent(Long clientId, Long contentId);
}
