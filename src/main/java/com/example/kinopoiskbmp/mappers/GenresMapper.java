package com.example.kinopoiskbmp.mappers;

import com.example.kinopoiskbmp.dto.GenresDTO;
import com.example.kinopoiskbmp.model.Genres;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenresMapper {

    GenresMapper INSTANCE = Mappers.getMapper(GenresMapper.class);

    GenresDTO toDTO(Genres genres);

}
