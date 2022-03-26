package com.example.kinopoiskbmp.mappers;

import com.example.kinopoiskbmp.dto.ContentTypesDTO;
import com.example.kinopoiskbmp.model.ContentTypes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContentTypesMapper {

    ContentTypesMapper INSTANCE = Mappers.getMapper(ContentTypesMapper.class);

    ContentTypesDTO toDTO(ContentTypes contentTypes);

}
