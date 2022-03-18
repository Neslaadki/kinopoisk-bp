package com.example.kinopoiskbmp.controllers;

import com.example.kinopoiskbmp.data.RequestReview;
import com.example.kinopoiskbmp.entities.Content;
import com.example.kinopoiskbmp.entities.ContentType;
import com.example.kinopoiskbmp.entities.Genre;
import com.example.kinopoiskbmp.entities.ReviewKey;
import com.example.kinopoiskbmp.services.ContentService;
import com.example.kinopoiskbmp.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        ReviewKey reviewKey = reviewService.sendReview(requestReview);
        return new HashMap<>() {{
            put("contentId", reviewKey.getContent().getId().toString());
            put("clientId", reviewKey.getClient().getId().toString());
        }};
    }


}
