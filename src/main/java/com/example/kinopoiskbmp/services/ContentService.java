package com.example.kinopoiskbmp.services;

import com.example.kinopoiskbmp.entities.Content;
import com.example.kinopoiskbmp.entities.ContentType;
import com.example.kinopoiskbmp.entities.Genre;
import com.example.kinopoiskbmp.exceptions.BadEmailValue;
import com.example.kinopoiskbmp.exceptions.InvalidRequestData;
import com.example.kinopoiskbmp.repositories.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;


    public Content getContentById(Long id){
        return contentRepository.getById(id);
    }

    public List<ContentType> getContentTypes() {
        return new ArrayList<>(Arrays.asList(ContentType.values()));
    }

    public List<Genre> getContentGenres() {
        return new ArrayList<>(Arrays.asList(Genre.values()));
    }

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
            throw new InvalidRequestData();
        }

    }

}
