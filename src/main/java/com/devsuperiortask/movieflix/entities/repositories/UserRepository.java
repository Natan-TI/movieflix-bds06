package com.devsuperiortask.movieflix.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperiortask.movieflix.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String name);

}
