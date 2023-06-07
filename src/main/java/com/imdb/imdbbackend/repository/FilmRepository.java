package com.imdb.imdbbackend.repository;

import com.imdb.imdbbackend.entity.Director;
import com.imdb.imdbbackend.entity.Film;
import com.imdb.imdbbackend.entity.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FilmRepository extends JpaRepository<Film, Long> {

	Page<Film> findAll(Pageable pageable);

	Optional<Film> findAllByTitleIgnoreCase(String title);

	Optional<Film> findAllByTitleContainingIgnoreCase(String title);

	List<Film> findByGenresOrDirectors(Genre genre, Director director);

}
