package com.example.kinopoiskbmp.controllers;

import com.example.kinopoiskbmp.dto.ContentDTO;
import com.example.kinopoiskbmp.dto.ContentTypesDTO;
import com.example.kinopoiskbmp.dto.GenresDTO;
import com.example.kinopoiskbmp.services.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/contents")
@RequiredArgsConstructor
public class ContentController {

    //TODO
    // - Какая-то валидация приимаемых данных, которые приходят в запросе!!
    // - BPMN
    // - Спецификация REST
    // - Добавить тестоых сценариев +

    private final ContentService contentService;


    @GetMapping("")
    public ResponseEntity<List<ContentDTO>> getContents() {
        return new ResponseEntity<>(contentService.getContents(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContentDTO> getContentsById(@PathVariable(name = "id") @Min(1) Long id) {
        return new ResponseEntity<>(contentService.getContentById(id), HttpStatus.OK);
    }


    @GetMapping(value = "/types")
    public ResponseEntity<List<ContentTypesDTO>> getContentTypes() {
        return new ResponseEntity<>(contentService.getContentTypes(), HttpStatus.OK);
    }

    @GetMapping("/types/{id}")
    public ResponseEntity<List<ContentDTO>> getContentsByType(@PathVariable(name = "id") @Min(1) Integer id) {
        return new ResponseEntity<>(contentService.getContentsByContendTypeId(id), HttpStatus.OK);
    }

    @GetMapping("/genres")
    public ResponseEntity<List<GenresDTO>> getContentGenre() {
        return new ResponseEntity<>(contentService.getGenres(), HttpStatus.OK);
    }

    @GetMapping("/genres/{id}")
    public ResponseEntity<List<ContentDTO>> getContentsByGenre(@PathVariable(name = "id") @Min(1) Integer id) {
        return new ResponseEntity<>(contentService.getContentsByGenreId(id), HttpStatus.OK);
    }


    @GetMapping("/genres/{genreId}/types/{typeId}")
    public ResponseEntity<List<ContentDTO>> getContentByGenreAndContentType(@PathVariable(name = "genreId") @Min(1) Integer genreId, @PathVariable(name = "typeId") @Min(1) Integer typeId) {
        return new ResponseEntity<>(contentService.getContentsByGenreIdAndTypeId(genreId, typeId), HttpStatus.OK);
    }


}
