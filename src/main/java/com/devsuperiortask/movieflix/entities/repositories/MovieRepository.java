package com.devsuperiortask.movieflix.entities.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperiortask.movieflix.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	
	@Query("SELECT obj FROM Movie obj JOIN FETCH obj.genre WHERE obj.id = :id")
	Optional<Movie> findByIdWithGenres(Long id);

	
	@Query("SELECT obj FROM Movie obj WHERE :genreId = 0L OR obj.genre.id = :genreId ORDER BY obj.title")
	Page<Movie> find(Long genreId, Pageable pageable);
}
