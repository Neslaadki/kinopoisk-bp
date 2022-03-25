package com.example.kinopoiskbmp.controllers;

import com.example.kinopoiskbmp.data.RequestReview;
import com.example.kinopoiskbmp.data.SendReviewResponse;
import com.example.kinopoiskbmp.entities.*;
import com.example.kinopoiskbmp.exceptions.BadEmailValue;
import com.example.kinopoiskbmp.exceptions.InvalidRequestData;
import com.example.kinopoiskbmp.services.ContentService;
import com.example.kinopoiskbmp.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebController {

    //TODO
    //какая-то валидация приимаемых данных

    @Autowired
    private ContentService contentService;
    @Autowired
    private ReviewService reviewService;


    @GetMapping(value = "/type")
    public ResponseEntity<List<ContentType>> getContentTypes() {
        return new ResponseEntity<>(contentService.getContentTypes(), HttpStatus.OK);
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Genre>> getContentGenre() {
        return new ResponseEntity<>(contentService.getContentGenres(), HttpStatus.OK);
    }

    @GetMapping("/type/{type}/genre/{genre}")
    public ResponseEntity<List<Content>> getContent(@PathVariable(name = "type") String type, @PathVariable(name = "genre") String genre) {
        return new ResponseEntity<>(contentService.getContentTypeByContentTypeOrGenre(genre, type), HttpStatus.OK);
    }

    @PostMapping(value = "/send", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SendReviewResponse> sendReview(@RequestBody RequestReview requestReview) {
        try {
            ReviewKey reviewKey = reviewService.sendReview(requestReview);
            return new ResponseEntity<>(
                    new SendReviewResponse()
                            .setContentId(reviewKey.getContent().getId())
                            .setClientId(reviewKey.getClient().getId()), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            throw new BadEmailValue();
        } catch (RuntimeException e) {
            throw new InvalidRequestData();
        }

    }

    @GetMapping("/content/{contentId}")
    public List<Review> getReviewByContent(@PathVariable(name = "contentId") Long contentId) {
        return reviewService.getReviewByContent(contentId);
    }

    @GetMapping("/content/{clientId}")
    public List<Review> getReviewByClient(@PathVariable(name = "clientId") Long clientId) {
        return reviewService.getReviewByClient(clientId);
    }

    @GetMapping("/content/{contentId}/client/{clientId}")
    public Review getReviewByClient(@PathVariable(name = "clientId") Long clientId, @PathVariable(name = "contentId") Long contentId) {
        return reviewService.getReviewByClientAndContent(clientId, contentId);
    }


}
