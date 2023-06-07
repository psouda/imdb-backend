package com.imdb.imdbbackend.service.mapper;

import com.imdb.imdbbackend.dto.response.FilmDTO;
import com.imdb.imdbbackend.entity.Film;
import com.imdb.imdbbackend.entity.Rating;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FilmMapper {

	private final ModelMapper modelMapper;

	public Film dtoToEntity(FilmDTO dto) {
		return modelMapper.map(dto, Film.class);
	}

	public void updateFromDto(FilmDTO dto, Film entity) {
		modelMapper.map(dto, entity);
	}

	public FilmDTO entityToDto(Film entity) {
		FilmDTO dto = modelMapper.map(entity, FilmDTO.class);
		if (null != entity.getRatings() && entity.getRatings().size() > 0) {
			dto.setAverageRating((float) entity.getRatings()
					.stream()
					.mapToInt(Rating::getScore)
					.average()
					.orElse(0));
		}
		return dto;
	}

}
