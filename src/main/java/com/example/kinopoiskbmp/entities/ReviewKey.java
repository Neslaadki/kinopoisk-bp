package com.example.kinopoiskbmp.entities;

import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class ReviewKey implements Serializable {

    @ManyToOne
    @MapsId
    private User user;
    @ManyToOne
    @MapsId
    private Content content;

}
