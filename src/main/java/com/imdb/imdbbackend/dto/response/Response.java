package com.imdb.imdbbackend.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Data
@SuperBuilder
@JsonInclude(NON_DEFAULT)
public class Response<T> {
	private LocalDateTime timeStamp;
	private T response;
	private int statusCode;
	private HttpStatus status;
	private String message;
	private String developerMessage;
	private T messageKey;
	private T error;
	private int currentPage;
	private long totalItems;
	private int totalPages;
}
