package com.example.kinopoiskbmp.mappers;

import com.example.kinopoiskbmp.dto.ClientDTO;
import com.example.kinopoiskbmp.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper( ClientMapper.class );

    ClientDTO toDTO(Client client);
}
