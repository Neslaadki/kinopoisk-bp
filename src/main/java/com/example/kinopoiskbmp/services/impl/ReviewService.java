package com.example.kinopoiskbmp.services.impl;

import com.example.kinopoiskbmp.dto.RequestReview;
import com.example.kinopoiskbmp.model.*;
import com.example.kinopoiskbmp.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService implements com.example.kinopoiskbmp.services.ReviewService {


    private final ReviewRepository reviewRepository;
    private final ClientServiceImpl clientServiceImpl;
    private final ContentService contentServiceImpl;


    //прочитать комментарии определенного человека к определенному контену

    @Override
    public ReviewKey sendReview(RequestReview r) {
        Content content = contentServiceImpl.getContentById(r.getContentId());
        if (content == null)
            return null;
        Client client = clientServiceImpl.addClient(
                new Client()
                        .setFirstName(r.getFirstName())
                        .setLastName(r.getLastName())
                        .setEmail(r.getEmail())
        );
        Review review = reviewRepository.save(
                new Review()
                        .setReviewKey(
                                new ReviewKey()
                                        .setClient(client)
                                        .setContent(content)
                        )
                        .setText(r.getText())
                        .setTopic(r.getTopic())
                        .setScoreType(ScoreType.getByName(r.getScore()) == null ? ScoreType.NEUTRAL : ScoreType.getByName(r.getScore()))
                        .setTime(new Timestamp(System.currentTimeMillis()))
        );
        return review.getReviewKey();
    }

    @Override
    public List<Review> getReviewByContent(Long contentId){
        return reviewRepository.getReviewByContent(contentId);
    }

    @Override
    public List<Review> getReviewByClient(Long clientId){
        return reviewRepository.getReviewByClient(clientId);
    }

    @Override
    public Review getReviewByClientAndContent(Long clientId, Long contentId){
        return reviewRepository.getReviewByClientAndContent(clientId, contentId);
    }

}
