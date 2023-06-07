package com.imdb.imdbbackend.service.security;

import com.imdb.imdbbackend.BaseTest;
import com.imdb.imdbbackend.Constant;
import com.imdb.imdbbackend.entity.User;
import com.imdb.imdbbackend.entity.security.Token;
import com.imdb.imdbbackend.repository.UserRepository;
import com.imdb.imdbbackend.repository.security.TokenRepository;
import com.imdb.imdbbackend.dto.request.security.AuthenticationRequest;
import com.imdb.imdbbackend.dto.request.security.RegisterRequest;
import com.imdb.imdbbackend.dto.response.security.AuthenticationResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthenticationServiceTest extends BaseTest {

	public static final String FAKETOKEN = "faketoken";
	public static final String FAKEPASS = "fakepass";

	@Mock
	private UserRepository repository;
	@Mock
	private TokenRepository tokenRepository;
	@Mock
	private PasswordEncoder passwordEncoder;
	@Mock
	private JwtService jwtService;
	@Mock
	private AuthenticationManager authenticationManager;

	@InjectMocks
	private AuthenticationService service;

	@BeforeEach
	void setUp() {
		when(jwtService.generateToken(any())).thenReturn(FAKETOKEN);
		when(tokenRepository.save(new Token())).thenReturn(new Token());
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testRegisterMethod() {
		when(repository.save(any())).thenReturn(new User());
		when(passwordEncoder.encode(any())).thenReturn(FAKEPASS);
		RegisterRequest request = RegisterRequest.builder()
				.firstname(Constant.FIRSTNAME)
				.lastname(Constant.LASTNAME)
				.email(Constant.EMAIL)
				.password(Constant.PASSWORD)
				.build();
		AuthenticationResponse actual = service.register(request);
		assertThat(actual.getToken()).isEqualTo(FAKETOKEN);
	}

	@Test
	void testAuthenticateMethod() {
		when(authenticationManager.authenticate(any())).thenReturn(null);
		when(repository.findByEmail(any())).thenReturn(Optional.of(User.builder().build()));
		AuthenticationResponse actual = service.authenticate(AuthenticationRequest.builder().email(Constant.EMAIL).password(Constant.PASSWORD).build());
		assertThat(actual.getToken()).isEqualTo(FAKETOKEN);
	}
}
