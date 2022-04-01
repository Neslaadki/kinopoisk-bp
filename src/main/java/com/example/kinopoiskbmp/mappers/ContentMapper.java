package com.example.kinopoiskbmp.mappers;

import com.example.kinopoiskbmp.dto.ContentDTO;
import com.example.kinopoiskbmp.model.Content;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContentMapper {

    ContentMapper INSTANCE = Mappers.getMapper( ContentMapper.class );

    @Mappings({
            @Mapping(source = "contentType.name", target = "typeName"),
            @Mapping(source = "genre.name", target = "genreName")
    })

    ContentDTO toDTO(Content content);

}
