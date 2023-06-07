package com.imdb.imdbbackend.service;

import com.imdb.imdbbackend.dto.response.FilmDTO;
import com.imdb.imdbbackend.entity.Rating;
import com.imdb.imdbbackend.entity.User;
import com.imdb.imdbbackend.exception.EntityNotFoundException;
import com.imdb.imdbbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

	public static final float SCORE_LIMIT = 3.0f;

	private final FilmService filmService;
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	public List<FilmDTO> getRecommendations(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
		List<FilmDTO> films = filmService
				.findByGenresOrDirectors(user.getFavouriteGenres(), user.getFavouriteDirectors())
				.stream()
				.map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList());
		List<FilmDTO> recommendedFilms = new ArrayList<>();
		for (FilmDTO film : films) {
			for (Rating rating : user.getRatings()) {
				if (!rating.getFilm().getId().equals(film.getId()) && film.getAverageRating() >= SCORE_LIMIT) {
					recommendedFilms.add(film);
				}
			}
		}
		return recommendedFilms;
	}
}