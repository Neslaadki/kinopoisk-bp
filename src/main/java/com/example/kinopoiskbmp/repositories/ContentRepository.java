package com.example.kinopoiskbmp.repositories;

import com.example.kinopoiskbmp.model.Content;
import com.example.kinopoiskbmp.model.ContentTypes;
import com.example.kinopoiskbmp.model.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> getContentByContentType(ContentTypes contentType);
    List<Content> getContentByGenre(Genres genre);
    List<Content> getContentByGenreAndContentType(Genres genre, ContentTypes contentTypes);
}
