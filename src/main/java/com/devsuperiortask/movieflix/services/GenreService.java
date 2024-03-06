package com.devsuperiortask.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperiortask.movieflix.dto.GenreDTO;
import com.devsuperiortask.movieflix.entities.Genre;
import com.devsuperiortask.movieflix.entities.repositories.GenreRepository;

@Service
public class GenreService {
	
	@Autowired
	private GenreRepository repository;
	
	@Transactional(readOnly = true)
	public List<GenreDTO> findAll(){
		List<Genre> genres = repository.findAll();
		return genres.stream().map(x -> new GenreDTO(x)).collect(Collectors.toList());
	}
}
