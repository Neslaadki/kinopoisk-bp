package com.example.kinopoiskbmp.services.impl;

import com.example.kinopoiskbmp.dto.ReviewDTO;
import com.example.kinopoiskbmp.dto.ReviewIncomingDTO;
import com.example.kinopoiskbmp.mappers.ReviewMapper;
import com.example.kinopoiskbmp.model.*;
import com.example.kinopoiskbmp.repositories.ContentRepository;
import com.example.kinopoiskbmp.repositories.ReviewRepository;
import com.example.kinopoiskbmp.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {


    private final ReviewRepository reviewRepository;
    private final ContentRepository contentRepository;

    private final ClientServiceImpl clientServiceImpl;
    private final ContentServiceImpl contentServiceImpl;
    private final ReviewMapper reviewMapper;


    @Override
    public void saveReview(ReviewIncomingDTO r) {
        if (!contentRepository.existsById(r.getContentId()))
            throw new PropertyNotFoundException("Content with this id is not not found!");
        Content content = contentRepository.getById(r.getContentId());
        Client client = clientServiceImpl.addClient(
                new Client()
                        .setFirstName(r.getFirstName())
                        .setLastName(r.getLastName())
                        .setEmail(r.getEmail())
        );
        reviewRepository.save(
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
    public List<ReviewDTO> getReviewByContent(@Min(1) Long contentId) {
        return reviewRepository.getReviewByContent(contentId)
                .stream().map(reviewMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> getReviewByClient(Long clientId) {
        return reviewRepository.getReviewByClient(clientId)
                .stream().map(reviewMapper::toDTO).collect(Collectors.toList());
    }


}
