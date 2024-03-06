package com.devsuperiortask.movieflix.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperiortask.movieflix.dto.MovieDTO;
import com.devsuperiortask.movieflix.dto.MovieDTO2;
import com.devsuperiortask.movieflix.dto.ReviewDTO;
import com.devsuperiortask.movieflix.entities.Genre;
import com.devsuperiortask.movieflix.entities.Movie;
import com.devsuperiortask.movieflix.entities.Review;
import com.devsuperiortask.movieflix.entities.repositories.GenreRepository;
import com.devsuperiortask.movieflix.entities.repositories.MovieRepository;
import com.devsuperiortask.movieflix.entities.repositories.ReviewRepository;
import com.devsuperiortask.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	@Autowired 
	private ReviewRepository reviewRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@PreAuthorize("hasAnyRole('VISITOR','MEMBER')")
	@Transactional(readOnly = true)
	public MovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findByIdWithGenres(id);
		Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new MovieDTO(entity, entity.getGenre());
	}
	
	@PreAuthorize("hasAnyRole('VISITOR','MEMBER')")
	@Transactional(readOnly = true)
	public List<ReviewDTO> reviewsById(Long id) {
		List<Review> list = reviewRepository.findReviewsById(id);
		return list.stream().map(x -> new ReviewDTO(x, x.getUser())).collect(Collectors.toList()); 
		
	}
	
	
	@PreAuthorize("hasAnyRole('VISITOR','MEMBER')")
	@Transactional(readOnly = true)
	public Page<MovieDTO2> findAllPaged(Long genreId, Pageable pageable){
		List<Genre> genres = (genreId == 0) ? null : Arrays.asList(genreRepository.getOne(genreId));
		Page<Movie> page = repository.find(genreId, pageable);
		//this.repository.findProductsWithCategories(page.getContent());
		return page.map(x -> new MovieDTO2(x));
	}
}
