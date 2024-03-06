package com.devsuperiortask.movieflix.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperiortask.movieflix.entities.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

}
