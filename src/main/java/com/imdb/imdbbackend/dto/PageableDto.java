package com.imdb.imdbbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class PageableDto {
	@JsonIgnore
	private int page;
	@JsonIgnore
	private long totalElements;
	@JsonIgnore
	private int totalPages;
}
