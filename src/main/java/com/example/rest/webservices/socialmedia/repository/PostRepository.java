package com.example.rest.webservices.socialmedia.repository;

import com.example.rest.webservices.socialmedia.entity.Post;
import com.example.rest.webservices.socialmedia.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findByUser(User user);
}
