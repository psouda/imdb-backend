package com.imdb.imdbbackend.repository;

import com.imdb.imdbbackend.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

	List<Genre> findByNameIn(List<String> name);

}