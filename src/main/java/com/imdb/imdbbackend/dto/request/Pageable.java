package com.imdb.imdbbackend.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * The pageable class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Pageable {
	@NotNull(message = "pageOrSortIncorrect")
	@Min(value = 0, message = "pageOrSortIncorrect")
	private int page = 0;
	@Min(value = 1, message = "pageOrSortIncorrect")
	@NotNull(message = "pageOrSortIncorrect")
	private int size = 100;
	@NotNull(message = "pageOrSortIncorrect")
	private Sort sort;
}
