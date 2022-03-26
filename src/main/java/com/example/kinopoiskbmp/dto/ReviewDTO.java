package com.example.kinopoiskbmp.dto;

import com.example.kinopoiskbmp.model.ScoreTypes;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
@Builder
public class ReviewDTO {

    private Long clientId;
    private Long contentId;
    private String topic;
    private String text;
    private String scoreType;
    private Timestamp time;

}
