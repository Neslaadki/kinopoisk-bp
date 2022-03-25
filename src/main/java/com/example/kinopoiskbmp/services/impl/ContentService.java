package com.example.kinopoiskbmp.services.impl;

import com.example.kinopoiskbmp.model.Content;
import com.example.kinopoiskbmp.model.ContentType;
import com.example.kinopoiskbmp.model.Genre;
import com.example.kinopoiskbmp.exceptions.InvalidRequestData;
import com.example.kinopoiskbmp.repositories.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService implements com.example.kinopoiskbmp.services.ContentService {

    private final ContentRepository contentRepository;


    @Override
    public Content getContentById(Long id){
        return contentRepository.getById(id);
    }

    @Override
    public List<ContentType> getContentTypes() {
        return new ArrayList<>(Arrays.asList(ContentType.values()));
    }

    @Override
    public List<Genre> getContentGenres() {
        return new ArrayList<>(Arrays.asList(Genre.values()));
    }

    @Override
    public List<Content> getContentTypeByContentTypeOrGenre(String g, String t) {
        try{
            Genre genre = Genre.getByName(g);
            ContentType contentType = ContentType.getByName(t);
            if (g.equals("") && t.equals(""))
                return contentRepository.findAll();
            if (t.equals(""))
                return contentRepository.getContentByGenre(genre);
            if (g.equals(""))
                return contentRepository.getContentByContentType(contentType);
            else
                return contentRepository.getContentByContentTypeAndGenre(contentType, genre);
        }catch (NullPointerException e){
            throw new InvalidRequestData("Params has invalid values");
        }

    }

}
