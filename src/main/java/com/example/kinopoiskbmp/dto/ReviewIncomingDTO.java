package com.example.kinopoiskbmp.dto;

import lombok.Data;

@Data
public class ReviewIncomingDTO {

    private Long contentId;
    /*
    client's fields
     */
    private String firstName;
    private String lastName;
    private String email;
    /*
    review's fields
     */
    private String topic;
    private String text;
    private String score;

}
