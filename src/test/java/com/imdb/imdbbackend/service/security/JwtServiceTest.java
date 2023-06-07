package com.imdb.imdbbackend.service.security;

import com.imdb.imdbbackend.BaseTest;
import com.imdb.imdbbackend.Constant;
import com.imdb.imdbbackend.entity.User;
import io.jsonwebtoken.Claims;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest extends BaseTest {

	public static final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtLmphY2tzb25AY2hlY2syNC5kZSIsImlhdCI6MTY3ODc4OTIzMywiZXhwIjoxNjc4NzkwNjczfQ.6N322R8Z-DJ0QYBoelVN8WY3k8oEYQgI-smoRS4zqwY";

	@InjectMocks
	private JwtService service;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void extractUsername() {
		String expected = "m.jackson@imdb.com";
		String actual = service.extractUsername(TOKEN);
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	@Ignore
	void extractClaim() {
		Date actual = service.extractClaim(TOKEN, Claims::getExpiration);
		assertThat(actual).isNotNull();
	}

	@Test
	void testGenerateToken() {
		String actual = service.generateToken(User.builder().firstname(Constant.FIRSTNAME).lastname(Constant.LASTNAME).email(Constant.EMAIL).password(Constant.PASSWORD).build());
		assertThat(actual).isNotNull();
	}

	@Test
	void isTokenValid() {
		boolean actual = service.isTokenValid(TOKEN, User.builder().firstname(Constant.FIRSTNAME).lastname(Constant.LASTNAME).email(Constant.EMAIL).password(Constant.PASSWORD).build());
		assertTrue(actual);
	}
}