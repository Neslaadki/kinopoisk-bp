package com.example.kinopoiskbmp.services.impl;

import com.example.kinopoiskbmp.dto.ReviewDTO;
import com.example.kinopoiskbmp.dto.ReviewIncomingDTO;
import com.example.kinopoiskbmp.mappers.ReviewMapper;
import com.example.kinopoiskbmp.model.*;
import com.example.kinopoiskbmp.repositories.ReviewRepository;
import com.example.kinopoiskbmp.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {


    private final ReviewRepository reviewRepository;
    private final ClientServiceImpl clientServiceImpl;
    private final ContentServiceImpl contentServiceImpl;
    private final ReviewMapper reviewMapper;


    //прочитать комментарии определенного человека к определенному контену

    @Override
    public void saveReview(ReviewIncomingDTO r) {
        Content content = contentServiceImpl.getContentById(r.getContentId());
        if (content == null)
            throw new RuntimeException();
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
                        .setScoreTypes(ScoreTypes.getByName(r.getScore()) == null ? ScoreTypes.NEUTRAL : ScoreTypes.getByName(r.getScore()))
                        .setTime(new Timestamp(System.currentTimeMillis()))
        );
    }

    @Override
    public List<ReviewDTO> getReviewByContent(Long contentId) {
        return reviewRepository.getReviewByContent(contentId)
                .stream().map(reviewMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewByClient(Long clientId) {
        return reviewRepository.getReviewByClient(clientId)
                .stream().map(reviewMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ReviewDTO getReviewByClientAndContent(Long clientId, Long contentId) {
        return reviewMapper.toDTO(reviewRepository.getReviewByClientAndContent(clientId, contentId));
    }

}
