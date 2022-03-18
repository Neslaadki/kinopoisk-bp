package com.example.kinopoiskbmp.repositories;

import com.example.kinopoiskbmp.entities.Content;
import com.example.kinopoiskbmp.entities.ContentType;
import com.example.kinopoiskbmp.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> getContentByContentType(ContentType contentType);
    List<Content> getContentByGenre(Genre genre);
    List<Content> getContentByContentTypeAndGenre(ContentType contentType, Genre genre);
}
