package com.example.kinopoiskbmp.controllers;

import com.example.kinopoiskbmp.dto.ContentDTO;
import com.example.kinopoiskbmp.dto.ContentTypesDTO;
import com.example.kinopoiskbmp.dto.GenresDTO;
import com.example.kinopoiskbmp.services.ContentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentController {

    //TODO
    // - Какая-то валидация приимаемых данных, которые приходят в запросе
    // - Возможно, отправлять в IncomingDTO не ФИ + email, а ID. Т.е. убрать возможность оставлять комментарии пользователям,
    // которых не в БД
    // принимать вместо названий ЖАНРА и ТИПА их id

    private final ContentService contentService;
    private static final Logger log = LoggerFactory.getLogger(ContentController.class);


    @GetMapping(value = "/types")
    public ResponseEntity<List<ContentTypesDTO>> getContentTypes() {
        return new ResponseEntity<>(contentService.getContentTypes(), HttpStatus.OK);
    }

    @GetMapping("/genres")
    public ResponseEntity<List<GenresDTO>> getContentGenre() {
        return new ResponseEntity<>(contentService.getGenres(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<ContentDTO>> getContents() {
        return new ResponseEntity<>(contentService.getContents(), HttpStatus.OK);
    }

    @GetMapping("/genre/{name}")
    public ResponseEntity<List<ContentDTO>> getContentsByGenre(@PathVariable(name = "name") String genreName) {
        return new ResponseEntity<>(contentService.getContentsByGenre(genreName), HttpStatus.OK);
    }

    @GetMapping("/types/{name}")
    public ResponseEntity<List<ContentDTO>> getContentsByType(@PathVariable(name = "name") String typeName) {
        return new ResponseEntity<>(contentService.getContentsByContendType(typeName), HttpStatus.OK);
    }

    @GetMapping("/genres/{genreName}/types/{typeName}")
    public ResponseEntity<List<ContentDTO>> getContent(@PathVariable(name = "genreName") String genreName, @PathVariable(name = "typeName") String typeName) {
        return new ResponseEntity<>(contentService.getContentsByTypeNameAndGenreName(genreName, typeName), HttpStatus.OK);
    }


}