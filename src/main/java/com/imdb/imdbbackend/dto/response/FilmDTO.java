package com.imdb.imdbbackend.dto.response;


import com.imdb.imdbbackend.dto.PageableDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class FilmDTO extends PageableDto {

	private Long id;
	private String title;
	private String description;
	private int releaseYear;
	private String language;
	private List<RatingDTO> ratings;
	private float averageRating;

}
