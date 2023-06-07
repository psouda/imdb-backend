package com.imdb.imdbbackend.controller;

import com.imdb.imdbbackend.dto.response.FilmDTO;
import com.imdb.imdbbackend.service.RecommendationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommendations")
@AllArgsConstructor
public class RecommendationController {

	private final RecommendationService recommendationService;

	@GetMapping("/{userId}")
	public List<FilmDTO> getRecommendations(@PathVariable Long userId) {
		return recommendationService.getRecommendations(userId);
	}
}
