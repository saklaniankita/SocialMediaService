package com.example.rest.webservices.socialmedia.repository;

import com.example.rest.webservices.socialmedia.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByIdAndName(Integer id, String name);

}
