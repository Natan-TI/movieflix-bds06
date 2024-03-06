package com.devsuperiortask.movieflix.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperiortask.movieflix.dto.UserDTO;
import com.devsuperiortask.movieflix.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping(value = "/profile")
	public ResponseEntity<UserDTO> getProfile() {
		UserDTO dto = null;
		try {
			dto = service.getProfile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(dto);
	}
}