package com.example.kinopoiskbmp.model;


import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Content {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ContentType contentType;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Genre genre;

}
