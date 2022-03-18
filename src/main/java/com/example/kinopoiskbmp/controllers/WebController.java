package com.example.kinopoiskbmp.controllers;

import com.example.kinopoiskbmp.data.RequestReview;
import com.example.kinopoiskbmp.entities.*;
import com.example.kinopoiskbmp.exceptions.BadEmailValue;
import com.example.kinopoiskbmp.services.ContentService;
import com.example.kinopoiskbmp.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/kinopoisk")
@RequiredArgsConstructor
public class WebController {

    @Autowired
    private ContentService contentService;
    @Autowired
    private ReviewService reviewService;


    @GetMapping(value = "/getContentTypes")
    public List<ContentType> getContentTypes() {
        return contentService.getContentTypes();
    }

    @GetMapping("/getContentGenre")
    public List<Genre> getContentGenre() {
        return contentService.getContentGenres();
    }

    @GetMapping("/getContent/{type}/{genre}")
    public List<Content> getContent(@PathVariable(name = "type") String type, @PathVariable(name = "genre") String genre) {
        return contentService.getContentTypeByContentTypeOrGenre(genre, type);
    }

    @PostMapping(value = "/sendReview", consumes = "application/json", produces = "application/json")
    public HashMap<String, String> sendReview(@RequestBody RequestReview requestReview) {
        try {
            ReviewKey reviewKey = reviewService.sendReview(requestReview);
            return new HashMap<>() {{
                put("contentId", reviewKey.getContent().getId().toString());
                put("clientId", reviewKey.getClient().getId().toString());
            }};
        }catch (ConstraintViolationException e){
            throw new BadEmailValue();
        }

    }

    @GetMapping("/getReviewByContent/{contentId}")
    public List<Review> getReviewByContent(@PathVariable(name = "contentId") Long contentId) {
       return reviewService.getReviewByContent(contentId);
    }

    @GetMapping("/getReviewByClient/{clientId}")
    public List<Review> getReviewByClient(@PathVariable(name = "clientId") Long clientId) {
        return reviewService.getReviewByClient(clientId);
    }

    @GetMapping("/getReviewByClientAndContent/{contentId}/{clientId}")
    public Review getReviewByClient(@PathVariable(name = "clientId") Long clientId,@PathVariable(name = "contentId") Long contentId) {
        return reviewService.getReviewByClientAndContent(clientId, contentId);
    }


}
