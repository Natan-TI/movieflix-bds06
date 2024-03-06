package com.devsuperiortask.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperiortask.movieflix.dto.ReviewDTO;
import com.devsuperiortask.movieflix.entities.Movie;
import com.devsuperiortask.movieflix.entities.Review;
import com.devsuperiortask.movieflix.entities.User;
import com.devsuperiortask.movieflix.entities.repositories.MovieRepository;
import com.devsuperiortask.movieflix.entities.repositories.ReviewRepository;
import com.devsuperiortask.movieflix.entities.repositories.UserRepository;
import com.devsuperiortask.movieflix.services.exceptions.ResourceNotFoundException;

import antlr.StringUtils;


@Service
public class ReviewService {

	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private AuthService authService;
	
	@PreAuthorize("hasAnyRole('MEMBER')")
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		if (dto.getText() == "") {
            throw new ResourceNotFoundException("Review text cannot be empty or blank");
        }
		Review entity = new Review();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ReviewDTO(entity, entity.getUser());
	}
	
	private void copyDtoToEntity(ReviewDTO dto, Review entity) {
		entity.setText(dto.getText());
		
		Long movieId = dto.getMovieId();
		Movie movie = movieRepository.getOne(movieId);
		entity.setMovie(movie);
	
		User user = authService.authenticated();
		entity.setUser(user);
	}
	
}
