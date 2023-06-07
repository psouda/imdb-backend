package com.imdb.imdbbackend.controller;

import com.imdb.imdbbackend.dto.request.AllFilmsRequest;
import com.imdb.imdbbackend.dto.response.FilmDTO;
import com.imdb.imdbbackend.dto.response.RatingDTO;
import com.imdb.imdbbackend.dto.response.Response;
import com.imdb.imdbbackend.service.FilmService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/v1/films")
@AllArgsConstructor
public class FilmController {

	private final FilmService service;

	@PostMapping
	public Response<FilmDTO> createMovie(@RequestBody FilmDTO dto) {
		return Response.<FilmDTO>builder()
				.response(service.createFilm(dto))
				.timeStamp(now())
				.status(HttpStatus.CREATED)
				.message("Film created successfully")
				.build();
	}

	@GetMapping
	public Response<List<FilmDTO>> getAllFilms(@Valid @RequestBody AllFilmsRequest request) {
		Page<FilmDTO> dto = service.getAllFilms(request);
		return Response.<List<FilmDTO>>builder()
				.response(dto.getContent())
				.totalItems(dto.getTotalElements())
				.currentPage(dto.getNumber())
				.totalPages(dto.getTotalPages())
				.timeStamp(now())
				.status(HttpStatus.OK)
				.build();
	}

	@GetMapping("/{id}")
	public Response<FilmDTO> getFilmById(@PathVariable(value="id") Long id) {
		return Response.<FilmDTO>builder()
				.response(service.getById(id))
				.timeStamp(now())
				.status(HttpStatus.OK)
				.build();
	}

	@GetMapping("/search")
	public Response<List<FilmDTO>> findAllByName(@RequestParam("title") String title) {
				return Response.<List<FilmDTO>>builder()
				.response(service.searchFilmsByTitle(title))
				.timeStamp(now())
				.status(HttpStatus.OK)
				.build();
	}

	@PostMapping("/{id}/rate")
	public Response<String> rateFilmById(@PathVariable("id") Long id, @RequestBody RatingDTO ratingDTO) {
		service.rateFilmById(id, ratingDTO);
		return Response.<String>builder()
				.response(null)
				.timeStamp(now())
				.status(HttpStatus.OK)
				.message("Film rated successfully")
				.build();
	}

}
