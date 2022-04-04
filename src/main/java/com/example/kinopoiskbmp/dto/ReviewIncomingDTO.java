package com.example.kinopoiskbmp.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ReviewIncomingDTO {

    @NotNull
    private Long contentId;
    /*
    client's fields
     */

    @NotNull
    private String firstName;
    private String lastName;

    @Email
    private String email;
    /*
    review's fields
     */
    @NotNull
    private String topic;
    @NotNull
    private String text;
    @NotNull
    private String score;

}
