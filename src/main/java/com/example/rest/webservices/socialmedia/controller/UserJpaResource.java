package com.example.rest.webservices.socialmedia.controller;

import com.example.rest.webservices.socialmedia.entity.Post;
import com.example.rest.webservices.socialmedia.entity.User;
import com.example.rest.webservices.socialmedia.exception.UserNotFoundException;
import com.example.rest.webservices.socialmedia.repository.PostRepository;
import com.example.rest.webservices.socialmedia.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJpaResource(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public User retrieveUserById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("User with id: " + id + " not found");
        return user.get();
    }

    @GetMapping("/jpa/users/{id}/{name}")
    public User retrieveUserByIdAndName(@PathVariable Integer id, @PathVariable String name) {
        Optional<User> user = userRepository.findByIdAndName(id, name);
        if (user.isEmpty())
            throw new UserNotFoundException("User with id: " + id + " and name: " + name + " not found");
        return user.get();
    }

    @GetMapping("/jpa/posts/{userId}")
    public List<Post> retrievePostsByUser(@PathVariable Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty())
            throw new UserNotFoundException("User with id: " + userId +" not found");
        return postRepository.findByUser(user.get());

    }

    /**
     * request for the current method is http://localhost:808/users
     * and to this request we are appending the id of the saved user ,
     * converting it to URI and returning this location with
     * Response status of 201 (CREATED)
     */
    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);

        //ServletUriComponentsBuilder.fromCurrentRequest().build() will return http://localhost:8080/jpa/users
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsByUserId(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("User with id: " + id + " not found");
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException("User with id: " + id + " not found");
        post.setId(user.get().getId());
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();


        return ResponseEntity.created(location).build();
    }

    /**
     * thsi method not working
     *
     * @param userId
     * @param postId
     * @return
     */
    @GetMapping("/jpa/users/{userId}/posts/{postId}")
    public void retrievePostByUserAndPostId(@PathVariable Integer userId, @PathVariable Integer postId) {
        System.out.println("I am in the method");
        Optional<User> user = userRepository.findById(userId);
        System.out.println(user);
//        if (user == null)
//            throw new UserNotFoundException("User with id: " + userId + " not found");
//        System.out.println("user: "+user);
//          List<Post> posts = user.getPosts();
//        System.out.println("Posts: "+posts);
//          Predicate<Post> predicate = e -> e.getId().equals(postId);
//        System.out.println(posts.stream().filter(predicate).findFirst().get());
//          return posts.stream().filter(predicate).findFirst().get();


    }
}
