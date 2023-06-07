package com.imdb.imdbbackend.repository;

import com.imdb.imdbbackend.entity.User;
import com.imdb.imdbbackend.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.imdb.imdbbackend.Constant.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryTest extends BaseTest {

	@Autowired
	UserRepository repository;

	@BeforeEach
	void setUp() {
		repository.save(User.builder().firstname(FIRSTNAME).lastname(LASTNAME).email(EMAIL).password(PASSWORD).build());
	}

	@AfterEach
	void tearDown() {
		repository.delete(User.builder().firstname(FIRSTNAME).lastname(LASTNAME).email(EMAIL).password(PASSWORD).build());
	}

	@Test
	void testFindByEmailMethod() {
		Optional<User> actual = repository.findByEmail(EMAIL);
		assertTrue(actual.isPresent());
	}

}