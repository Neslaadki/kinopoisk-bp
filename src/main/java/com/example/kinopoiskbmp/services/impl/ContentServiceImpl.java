package com.example.kinopoiskbmp.services.impl;

import com.example.kinopoiskbmp.dto.ContentTypesDTO;
import com.example.kinopoiskbmp.dto.GenresDTO;
import com.example.kinopoiskbmp.mappers.ContentMapper;
import com.example.kinopoiskbmp.mappers.ContentTypesMapper;
import com.example.kinopoiskbmp.mappers.GenresMapper;
import com.example.kinopoiskbmp.model.Content;
import com.example.kinopoiskbmp.model.ContentTypes;
import com.example.kinopoiskbmp.model.Genres;
import com.example.kinopoiskbmp.exceptions.InvalidRequestData;
import com.example.kinopoiskbmp.repositories.ContentRepository;
import com.example.kinopoiskbmp.services.ContentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    private final ContentTypesMapper contentTypesMapper;
    private final GenresMapper genresMapper;


    @Override
    public Content getContentById(Long id){
        return contentRepository.getById(id);
    }

    @Override
    public List<ContentTypesDTO>getContentTypes() {
        return Arrays.stream(ContentTypes.values())
                .map(contentTypesMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<GenresDTO> getContentGenres() {
        return Arrays.stream(Genres.values())
                .map(genresMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<Content> getContentTypeByContentTypeOrGenre(String g, String t) {
        try{
            Genres genre = Genres.getByName(g);
            ContentTypes contentType = ContentTypes.getByName(t);
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
