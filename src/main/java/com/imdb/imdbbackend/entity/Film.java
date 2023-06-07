package com.imdb.imdbbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "films")
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	private String description;

	private int releaseYear;

	private String language;

	@OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@ToString.Exclude
	private List<Rating> ratings = new ArrayList<>();

	@OneToMany(mappedBy = "film")
	@ToString.Exclude
	private List<Director> directors = new ArrayList<>();

	@OneToMany(mappedBy = "film")
	@ToString.Exclude
	private List<Genre> genres = new ArrayList<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Film film = (Film) o;
		return id != null && Objects.equals(id, film.id);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

}