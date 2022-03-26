package com.example.kinopoiskbmp.services;

import com.example.kinopoiskbmp.dto.ContentDTO;
import com.example.kinopoiskbmp.dto.ContentTypesDTO;
import com.example.kinopoiskbmp.dto.GenresDTO;
import com.example.kinopoiskbmp.model.Content;

import java.util.List;

public interface ContentService {

    Content getContentById(Long id);

    List<ContentTypesDTO> getContentTypes();

    List<GenresDTO> getGenres();

    List<ContentDTO> getContentsByTypeNameAndGenreName(String genreName, String typeName);

    List<ContentDTO> getContentsByContendType(String TypeName);

    List<ContentDTO> getContentsByGenre(String genreName);

    List<ContentDTO> getContents();

}
