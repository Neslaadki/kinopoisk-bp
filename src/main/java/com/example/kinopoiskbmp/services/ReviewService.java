package com.example.kinopoiskbmp.services;

import com.example.kinopoiskbmp.data.RequestReview;
import com.example.kinopoiskbmp.entities.*;
import com.example.kinopoiskbmp.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {


    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ContentService contentService;


    //прочитать комментарии определенного человека к определенному контену

    public ReviewKey sendReview(RequestReview r) {
        Content content = contentService.getContentById(r.getContentId());
        if (content == null)
            return null;
        Client client = clientService.addClient(
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

    public List<Review> getReviewByContent(Long contentId){
        return reviewRepository.getReviewByContent(contentId);
    }

    public List<Review> getReviewByClient(Long clientId){
        return reviewRepository.getReviewByClient(clientId);
    }

    public Review getReviewByClientAndContent(Long clientId, Long contentId){
        return reviewRepository.getReviewByClientAndContent(clientId, contentId);
    }

}
