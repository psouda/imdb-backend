package com.imdb.imdbbackend.repository;

import com.imdb.imdbbackend.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DirectorRepository extends JpaRepository<Director, Long> {

	List<Director> findByFullNameIn(List<String> fullNames);

}