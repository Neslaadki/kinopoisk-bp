package com.example.kinopoiskbmp.controllers;

import com.example.kinopoiskbmp.dto.ContentTypesDTO;
import com.example.kinopoiskbmp.dto.GenresDTO;
import com.example.kinopoiskbmp.dto.ReviewIncomingDTO;
import com.example.kinopoiskbmp.model.Content;
import com.example.kinopoiskbmp.model.Genres;
import com.example.kinopoiskbmp.model.Review;
import com.example.kinopoiskbmp.services.ContentService;
import com.example.kinopoiskbmp.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
public class WebController {

    //TODO
    // - Какая-то валидация приимаемых данных
    // - Разбить функцию отправки POST send на три разные (без параметров и с параметрами)
    // - Возможно, отправлять в IncomingDTO не ФИ + email, а ID. Т.е. убрать возможность оставлять комментарии пользователям,
    // которых не в БД

    private final ContentService contentService;

    private final ReviewService reviewService;

    private static final Logger log =
            LoggerFactory.getLogger(WebController.class);


    @GetMapping(value = "/types")
    public ResponseEntity<List<ContentTypesDTO>> getContentTypes() {
        return new ResponseEntity<>(contentService.getContentTypes(), HttpStatus.OK);
    }

    @GetMapping("/genres")
    public ResponseEntity<List<GenresDTO>> getContentGenre() {
        return new ResponseEntity<>(contentService.getContentGenres(), HttpStatus.OK);
    }

    @GetMapping("/types/{type}/genres/{genre}")
    public ResponseEntity<List<Content>> getContent(@PathVariable(name = "type") String type, @PathVariable(name = "genre") String genre) {
        return new ResponseEntity<>(contentService.getContentTypeByContentTypeOrGenre(genre, type), HttpStatus.OK);
    }

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

    @GetMapping("reviews/contents/{contentId}")
    public ResponseEntity<?> getReviewByContent(@PathVariable(name = "contentId") Long contentId) {
        log.info("Request to getReview by contentId: {}", contentId);
        return new ResponseEntity<>(reviewService.getReviewByContent(contentId), HttpStatus.OK);
    }

    @GetMapping("reviews/clients/{clientId}")
    public ResponseEntity<?> getReviewByClient(@PathVariable(name = "clientId") Long clientId) {
        return new ResponseEntity<>(reviewService.getReviewByClient(clientId), HttpStatus.OK);
    }

    @GetMapping("/{contentId}/clients/{clientId}")
    public ResponseEntity<?> getReviewByClient(@PathVariable(name = "clientId") Long clientId, @PathVariable(name = "contentId") Long contentId) {
        return new ResponseEntity<>(reviewService.getReviewByClientAndContent(clientId, contentId), HttpStatus.OK);
    }


}
