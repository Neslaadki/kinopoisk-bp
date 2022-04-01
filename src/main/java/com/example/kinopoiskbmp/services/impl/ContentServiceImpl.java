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

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;

    private final ContentTypesMapper contentTypesMapper;
    private final GenresMapper genresMapper;
    private final ContentMapper contentMapper;

    private Map<Integer, String> genresMap;
    private Map<Integer, String> typesMap;

    @PostConstruct
    private void createMaps() {
        genresMap = Arrays.stream(Genres.values()).collect(Collectors.toMap(Genres::getId, Genres::getName));
        typesMap = Arrays.stream(ContentTypes.values()).collect(Collectors.toMap(ContentTypes::getId, ContentTypes::getName));
    }


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
    public List<ContentDTO> getContentsByContendTypeId(Integer id) {
        try {
            return contentRepository.getContentByContentType(ContentTypes.getByName(typesMap.get(id)))
                    .stream().map(contentMapper::toDTO).collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new InvalidRequestData("Param has invalid value");
        }
    }

    @Override
    public List<ContentDTO> getContentsByGenreId(Integer id) {
        try {
            return contentRepository.getContentByGenre(Genres.getGenreByName(genresMap.get(id)))
                    .stream().map(contentMapper::toDTO).collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new InvalidRequestData("Param has invalid value");
        }
    }


    @Override
    public List<ContentDTO> getContentsByGenreIdAndTypeId(Integer genreId, Integer typeId) {
        try {
            return contentRepository.getContentByGenreAndContentType(Genres.getGenreByName(genresMap.get(genreId)),
                            ContentTypes.getByName(typesMap.get(typeId)))
                    .stream().map(contentMapper::toDTO).collect(Collectors.toList());
        } catch (NullPointerException e) {
            throw new InvalidRequestData("Params has invalid values");
        }

    }
}
