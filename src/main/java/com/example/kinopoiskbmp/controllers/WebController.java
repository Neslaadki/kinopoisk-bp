package com.example.kinopoiskbmp.controllers;

import com.example.kinopoiskbmp.dto.RequestReview;
import com.example.kinopoiskbmp.dto.SendReviewResponse;
import com.example.kinopoiskbmp.exceptions.BadEmailValue;
import com.example.kinopoiskbmp.exceptions.InvalidRequestData;
import com.example.kinopoiskbmp.model.*;
import com.example.kinopoiskbmp.services.impl.ContentService;
import com.example.kinopoiskbmp.services.impl.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/content")
@RequiredArgsConstructor
public class WebController {

    //TODO
    //какая-то валидация приимаемых данных
    //разбить функцию отправки POST send на три разные (без параметров и с параметрами)

    private final ContentService contentService;
    private final ReviewService reviewService;


    @GetMapping(value = "/types")
    public ResponseEntity<List<ContentType>> getContentTypes() {
        return new ResponseEntity<>(contentService.getContentTypes(), HttpStatus.OK);
    }

    @GetMapping("/genres")
    public ResponseEntity<List<Genre>> getContentGenre() {
        return new ResponseEntity<>(contentService.getContentGenres(), HttpStatus.OK);
    }

    @GetMapping("/types/{type}/genres/{genre}")
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
            throw new BadEmailValue("Invalid email format");
        } catch (RuntimeException e) {
            throw new InvalidRequestData("Invalid data format");
        }

    }

    @GetMapping("/contents/{contentId}")
    public List<Review> getReviewByContent(@PathVariable(name = "contentId") Long contentId) {
        return reviewService.getReviewByContent(contentId);
    }

    @GetMapping("/contents/{clientId}")
    public List<Review> getReviewByClient(@PathVariable(name = "clientId") Long clientId) {
        return reviewService.getReviewByClient(clientId);
    }

    @GetMapping("/contents/{contentId}/clients/{clientId}")
    public Review getReviewByClient(@PathVariable(name = "clientId") Long clientId, @PathVariable(name = "contentId") Long contentId) {
        return reviewService.getReviewByClientAndContent(clientId, contentId);
    }


}
