package com.example.kinopoiskbmp.services.impl;

import com.example.kinopoiskbmp.dto.ContentDTO;
import com.example.kinopoiskbmp.dto.ContentTypesDTO;
import com.example.kinopoiskbmp.dto.GenresDTO;
import com.example.kinopoiskbmp.exceptions.InvalidRequestData;
import com.example.kinopoiskbmp.mappers.ContentMapper;
import com.example.kinopoiskbmp.mappers.ContentTypesMapper;
import com.example.kinopoiskbmp.mappers.GenresMapper;
import com.example.kinopoiskbmp.model.Content;
import com.example.kinopoiskbmp.model.ContentTypes;
import com.example.kinopoiskbmp.model.Genres;
import com.example.kinopoiskbmp.repositories.ContentRepository;
import com.example.kinopoiskbmp.services.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    private final ContentTypesMapper contentTypesMapper;
    private final GenresMapper genresMapper;
    private final ContentMapper contentMapper;


    @Override
    public Content getContentById(Long id) {
        return contentRepository.getById(id);
    }

    @Override
    public List<ContentTypesDTO> getContentTypes() {
        return Arrays.stream(ContentTypes.values())
                .map(contentTypesMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<GenresDTO> getGenres() {
        return Arrays.stream(Genres.values())
                .map(genresMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ContentDTO> getContents() {
        return contentRepository.findAll()
                .stream().map(contentMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ContentDTO> getContentsByContendType(String typeName) {
        try {
            return contentRepository.getContentByContentType(ContentTypes.getByName(typeName))
                    .stream().map(contentMapper::toDTO).collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new InvalidRequestData("Param has invalid value");
        }
    }

    @Override
    public List<ContentDTO> getContentsByGenre(String genreName) {
        try {
            return contentRepository.getContentByGenre(Genres.getByName(genreName))
                    .stream().map(contentMapper::toDTO).collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new InvalidRequestData("Param has invalid value");
        }
    }


    @Override
    public List<ContentDTO> getContentsByTypeNameAndGenreName(String genreName, String typeName) {
        try {
            return contentRepository.getContentByContentTypeAndGenre(ContentTypes.getByName(typeName), Genres.getByName(genreName))
                    .stream().map(contentMapper::toDTO).collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new InvalidRequestData("Params has invalid values");
        }

    }
}
