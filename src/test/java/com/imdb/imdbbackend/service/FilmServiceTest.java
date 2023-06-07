package com.imdb.imdbbackend.service;

import com.imdb.imdbbackend.BaseTest;
import com.imdb.imdbbackend.Constant;
import com.imdb.imdbbackend.dto.response.RatingDTO;
import com.imdb.imdbbackend.entity.Film;
import com.imdb.imdbbackend.entity.Rating;
import com.imdb.imdbbackend.entity.User;
import com.imdb.imdbbackend.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FilmServiceTest extends BaseTest {

	@Mock
	private FilmRepository filmRepository;

	@InjectMocks
	private FilmService filmService;

	@Captor
	private ArgumentCaptor<Film> filmCaptor;

	@Test
	public void testRateFilmById() {
		Long filmId = 1L;
		Film film = new Film();
		film.setId(filmId);
		film.setTitle("Test Film");

		// set up mock repository behavior
		when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));

		RatingDTO ratingDTO = new RatingDTO();
		ratingDTO.setScore(4);

		User user = new User();
		user.setId(1L);
		user.setEmail(Constant.EMAIL);

		// set up mock security context with test user
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null);
		SecurityContextHolder.getContext().setAuthentication(auth);

		// call the method being tested
		filmService.rateFilmById(filmId, ratingDTO);

		// verify that the film was saved with the new rating
		verify(filmRepository).save(filmCaptor.capture());
		Film savedFilm = filmCaptor.getValue();

		assertEquals(filmId, savedFilm.getId());
		assertEquals("Test Film", savedFilm.getTitle());
		assertEquals(1, savedFilm.getRatings().size());

		Rating savedRating = savedFilm.getRatings().get(0);
		assertEquals(4, savedRating.getScore());
		assertEquals(user.getId(), savedRating.getUser().getId());
	}

}
