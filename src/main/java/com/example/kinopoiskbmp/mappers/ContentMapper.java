package com.example.kinopoiskbmp.mappers;

import com.example.kinopoiskbmp.dto.ContentDTO;
import com.example.kinopoiskbmp.model.Content;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContentMapper {

    ContentMapper INSTANCE = Mappers.getMapper( ContentMapper.class );

    ContentDTO toDTO(Content content);

}
