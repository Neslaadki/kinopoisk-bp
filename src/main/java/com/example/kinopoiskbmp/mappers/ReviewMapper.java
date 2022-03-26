package com.example.kinopoiskbmp.mappers;


import com.example.kinopoiskbmp.dto.ReviewDTO;
import com.example.kinopoiskbmp.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper( ReviewMapper.class );

    @Mappings({
            @Mapping(source = "reviewKey.client.id", target = "clientId"),
            @Mapping(source = "reviewKey.content.id", target = "contentId"),
            @Mapping(source = "scoreTypes.name", target = "scoreType")
    })
    ReviewDTO toDTO(Review review);

}
