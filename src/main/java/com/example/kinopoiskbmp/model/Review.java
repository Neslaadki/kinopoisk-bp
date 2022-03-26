package com.example.kinopoiskbmp.model;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Review  {

    @EmbeddedId
    private ReviewKey reviewKey;
    private String topic;
    private String text;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ScoreTypes scoreTypes;
    @NotNull
    private Timestamp time;

}
