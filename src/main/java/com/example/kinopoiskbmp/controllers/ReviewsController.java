package com.example.kinopoiskbmp.controllers;

import com.example.kinopoiskbmp.dto.ReviewIncomingDTO;
import com.example.kinopoiskbmp.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewsController {

    private static final Logger log = LoggerFactory.getLogger(ReviewsController.class);
    private final ReviewService reviewService;


    @PostMapping(value = "/review", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void postReview(@RequestBody ReviewIncomingDTO reviewIncomingDTO) {
        log.info("Request to create review: {}", reviewIncomingDTO.getContentId());
        try {
            reviewService.saveReview(reviewIncomingDTO);
        } catch (ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/contents/{contentId}")
    public ResponseEntity<?> getReviewByContent(@PathVariable(name = "contentId") Long contentId) {
        log.info("Request to getReview by contentId: {}", contentId);
        return new ResponseEntity<>(reviewService.getReviewByContent(contentId), HttpStatus.OK);
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<?> getReviewByClient(@PathVariable(name = "clientId") Long clientId) {
        return new ResponseEntity<>(reviewService.getReviewByClient(clientId), HttpStatus.OK);
    }

    /*
    TODO
     - Дерьмово выглядит
     */

    @GetMapping("/{contentId}/clients/{clientId}")
    public ResponseEntity<?> getReviewByContentAndClient(@PathVariable(name = "clientId") Long clientId, @PathVariable(name = "contentId") Long contentId) {
        return new ResponseEntity<>(reviewService.getReviewByClientAndContent(clientId, contentId), HttpStatus.OK);
    }
}
