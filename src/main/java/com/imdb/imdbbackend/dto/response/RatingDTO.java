package com.imdb.imdbbackend.dto.response;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RatingDTO {

	private Long filmId;

	@Min(value = 1, message = "the score is under 1")
	@Max(5)
	private int score;

}
