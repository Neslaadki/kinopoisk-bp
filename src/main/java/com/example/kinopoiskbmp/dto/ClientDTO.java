package com.example.kinopoiskbmp.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors (chain = true)
public class ClientDTO {

    private String firstName;
    private String lastName;
    private String email;

}
