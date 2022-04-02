package com.example.kinopoiskbmp.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ReviewIncomingDTO {

    @Min(1)
    private Long contentId;
    /*
    client's fields
     */

    private String firstName;
    private String lastName;

    @Pattern(regexp = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")
    private String email;
    /*
    review's fields
     */
    @NotNull
    private String topic;
    @NotNull
    private String text;
    private String score;

}
