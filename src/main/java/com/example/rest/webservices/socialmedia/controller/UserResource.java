package com.example.rest.webservices.socialmedia.controller;

import com.example.rest.webservices.socialmedia.entity.User;
import com.example.rest.webservices.socialmedia.service.UserDaoService;
import com.example.rest.webservices.socialmedia.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService userDaoService;
    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUserById(@PathVariable Integer id) {
        User user = userDaoService.findUserById(id);
        if (user == null)
            throw new UserNotFoundException("User with id: " + id + " not found");
        return user;
    }

    /**
     * request for the current method is http://localhost:808/users
     * and to this request we are appending the id of the saved user ,
     * converting it to URI and returning this location with
     * Response status of 201 (CREATED)
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);

        //ServletUriComponentsBuilder.fromCurrentRequest().build() will return http://localhost:8080/users
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        userDaoService.deleteById(id);
    }
}
