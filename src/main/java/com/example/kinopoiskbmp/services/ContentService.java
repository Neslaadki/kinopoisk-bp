package com.example.kinopoiskbmp.services;

import com.example.kinopoiskbmp.dto.ContentDTO;
import com.example.kinopoiskbmp.dto.ContentTypesDTO;
import com.example.kinopoiskbmp.dto.GenresDTO;
import com.example.kinopoiskbmp.model.Content;

import java.util.List;

public interface ContentService {

    ContentDTO getContentById(Long id);

    List<ContentTypesDTO> getContentTypes();

    List<GenresDTO> getGenres();

    List<ContentDTO> getContentsByGenreIdAndTypeId(Integer genreId, Integer typeId);

    List<ContentDTO> getContentsByContendTypeId(Integer id);

    List<ContentDTO> getContentsByGenreId(Integer id);

    List<ContentDTO> getContents();

}
