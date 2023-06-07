package com.imdb.imdbbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imdb.imdbbackend.Constant;
import com.imdb.imdbbackend.dto.response.FilmDTO;
import com.imdb.imdbbackend.service.RecommendationService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Key;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = Constant.EMAIL, password = Constant.PASSWORD, authorities = "USER")
public class RecommendationControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private RecommendationService recommendationService;

	private final String JWT_SECRET = "EC2543C0EB6A307C1C16BA281C515A872D784936B83A711913558CD47093712C";
	private final long JWT_EXPIRATION_MS = 3600000; // 1 hour

	private String generateJwtToken(String username) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION_MS);
		return Jwts
				.builder()
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	@Test
	public void testGetRecommendations() throws Exception {
		Long userId = 1L;
		FilmDTO filmDTO = new FilmDTO();
		filmDTO.setId(1L);
		filmDTO.setTitle("Test Film");
		List<FilmDTO> filmDTOList = Collections.singletonList(filmDTO);
		given(recommendationService.getRecommendations(userId)).willReturn(filmDTOList);

		String jwtToken = generateJwtToken(Constant.EMAIL);

		mockMvc.perform(get("/api/v1/recommendations/" + userId)
						.header("Authorization", "Bearer " + jwtToken)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(filmDTOList)));
	}

	private Key getSignInKey() {
		String SECRET_KEY = "EC2543C0EB6A307C1C16BA281C515A872D784936B83A711913558CD47093712C";
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}