package com.example.kinopoiskbmp.entities;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
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
    private Integer mark;
    private Timestamp time;

}
