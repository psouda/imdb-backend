package com.imdb.imdbbackend.exception.handler;

import com.imdb.imdbbackend.exception.EntityNotFoundException;
import com.imdb.imdbbackend.exception.ServerException;
import com.imdb.imdbbackend.dto.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ValidationAdvice {

	@ResponseStatus(BAD_REQUEST)
	@ResponseBody
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Response<Object> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
		return Response.<Object>builder()
				.messageKey(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage())
				.status(HttpStatus.BAD_REQUEST)
				.statusCode(HttpStatus.BAD_REQUEST.value())
				.timeStamp(now())
				.build();
	}

	@ExceptionHandler(value = {ServerException.class})
	public Response<Object> handleServerException() {
		return Response.<Object>builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error("An unknown error occurred.")
				.timeStamp(now())
				.build();
	}


	@ExceptionHandler(value = {EntityNotFoundException.class})
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public Response<Object> handleNotFoundException() {
		return Response.<Object>builder()
				.error("Entity not found.")
				.timeStamp(now())
				.build();
	}

	@ExceptionHandler(value = {UsernameNotFoundException.class})
	@ResponseBody
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Response<Object> userNotFoundException() {
		return Response.<Object>builder()
				.error("Forbidden.")
				.timeStamp(now())
				.build();
	}

}