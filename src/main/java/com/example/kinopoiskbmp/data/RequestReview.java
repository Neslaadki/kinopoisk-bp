package com.example.kinopoiskbmp.data;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors (chain = true)
public class RequestReview {

    private String firstName;
    private String lastName;
    private String email;
    private String topic;
    private String text;
    private String score;
    private Long contentId;

}
