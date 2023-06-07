package com.imdb.imdbbackend.service;

import com.imdb.imdbbackend.dto.request.AllFilmsRequest;
import com.imdb.imdbbackend.dto.response.FilmDTO;
import com.imdb.imdbbackend.entity.*;
import com.imdb.imdbbackend.exception.EntityNotFoundException;
import com.imdb.imdbbackend.repository.FilmRepository;
import com.imdb.imdbbackend.service.mapper.FilmMapper;
import com.imdb.imdbbackend.dto.response.RatingDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.imdb.imdbbackend.util.RequestUtil.getPageableFromRequest;

@Service
@RequiredArgsConstructor
public class FilmService {

	private final FilmRepository repository;
	private final FilmMapper mapper;

	public Page<FilmDTO> getAllFilms(AllFilmsRequest request) {
		return repository
				.findAll(getPageableFromRequest(request.getPageable(), false))
				.map(mapper::entityToDto);
	}

	public FilmDTO getById(Long id) {
		Optional<Film> optional = repository.findById(id);
		if (optional.isPresent()) {
			return mapper.entityToDto(optional.get());
		} else {
			throw new EntityNotFoundException();
		}
	}

	public List<FilmDTO> searchFilmsByTitle(String title) {
		Optional<Film> films = repository.findAllByTitleContainingIgnoreCase(title);
		return films.stream().map(mapper::entityToDto).collect(Collectors.toList());
	}

	public List<FilmDTO> findAllFilmByName(String name) {
		Optional<Film> films = repository.findAllByTitleContainingIgnoreCase(name);
		return films.stream().map(mapper::entityToDto).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public void rateFilmById(Long id, RatingDTO ratingDTO) {
		Film film = repository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Film not found with id: " + id));
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Rating rating = Rating.builder().score(scaleScore(ratingDTO.getScore())).user(user).film(film).build();
		film.getRatings().add(rating);
		repository.save(film);
	}

	public List<Film> findByGenresOrDirectors(List<Genre> genres, List<Director> directors) {
		List<Film> films = new ArrayList<>();
		for (Genre genre : genres) {
			for (Director director : directors) {
				films.addAll(repository.findByGenresOrDirectors(genre, director));
			}
		}
		return films;
	}

	public FilmDTO createFilm(FilmDTO dto) {
		return mapper.entityToDto(repository.save(mapper.dtoToEntity(dto)));
	}

	private int scaleScore(int score) {
		if (score < 1) {
			return 1;
		} else if (score > 5) {
			return 5;
		}
		return 0;
	}
}
