package com.devsuperiortask.movieflix.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperiortask.movieflix.dto.MovieDTO;
import com.devsuperiortask.movieflix.dto.MovieDTO2;
import com.devsuperiortask.movieflix.dto.ReviewDTO;
import com.devsuperiortask.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {
	
	@Autowired
	private MovieService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		MovieDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/{id}/reviews")
	public ResponseEntity<List<ReviewDTO>> reviewsById(@PathVariable Long id) {
		List<ReviewDTO >list = service.reviewsById(id);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
	public ResponseEntity<Page<MovieDTO2>> findAll(
			@RequestParam(value = "genreId", defaultValue = "0") Long genreId,
			Pageable pageable){
		Page<MovieDTO2> list = service.findAllPaged(genreId, pageable);
		return ResponseEntity.ok().body(list);
	}
}
