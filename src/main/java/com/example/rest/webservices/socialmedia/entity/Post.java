package com.example.rest.webservices.socialmedia.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity    //jakarta persistence annotation
@Getter   //lombok annotation
@Setter   //lombok annotation
@NoArgsConstructor    //lombok annotation
//@ToString(includeFieldNames = false)
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    @ManyToOne
    @JsonIgnore
    private User user;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    @Override
    public String toString() {
        return "Post{" +
                "description='" + description + '\'' +
                '}';
    }
}
