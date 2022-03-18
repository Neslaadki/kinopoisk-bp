package com.example.kinopoiskbmp.entities;

import com.sun.xml.bind.v2.TODO;
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
    @Enumerated(EnumType.STRING)
    private ScoreType scoreType;
    private Timestamp time;

}
