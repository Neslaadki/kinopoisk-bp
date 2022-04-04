package com.example.kinopoiskbmp.controllers;

import com.example.kinopoiskbmp.dto.ReviewIncomingDTO;
import com.example.kinopoiskbmp.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewsController {

    private static final Logger log = LoggerFactory.getLogger(ReviewsController.class);
    private final ReviewService reviewService;


    @PostMapping(value = "/review", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void postReview(@Valid @RequestBody ReviewIncomingDTO reviewIncomingDTO) {
        log.info("Request to create review: {}", reviewIncomingDTO.getEmail());
        try {
            reviewService.saveReview(reviewIncomingDTO);
        } catch (ConstraintViolationException | PropertyNotFoundException | RollbackException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.toString());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/contents/{contentId}")
    public ResponseEntity<?> getReviewByContent(@PathVariable(name = "contentId") @Min(1L) Long contentId) {
        log.info("Request to getReview by contentId: {}", contentId);
        return new ResponseEntity<>(reviewService.getReviewByContent(contentId), HttpStatus.OK);
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<?> getReviewByClient(@PathVariable(name = "clientId") @Min(1L) Long clientId) {
        return new ResponseEntity<>(reviewService.getReviewByClient(clientId), HttpStatus.OK);
    }


}
