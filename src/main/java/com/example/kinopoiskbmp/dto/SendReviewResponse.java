package com.example.kinopoiskbmp.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors (chain = true)
public class SendReviewResponse {

    private Long contentId;
    private Long clientId;

}
