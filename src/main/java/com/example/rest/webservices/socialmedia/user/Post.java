package com.example.rest.webservices.socialmedia.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity    //jakarta persistence annotation
@Getter   //lombok annotation
@Setter   //lombok annotation
@NoArgsConstructor    //lombok annotation
@ToString(includeFieldNames = true)
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    @ManyToOne
    @JsonIgnore
    private User user;
//
//    No args Constructor is required but is provided by lombok
//    public Post() {
//    }

    public Post(String description, User user) {
        this.description = description;
        this.user = user;
    }

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

//    @Override
//    public String toString() {
//        return "Post{" +
//                "id=" + id +
//                ", description='" + description + '\'' +
//                ", user=" + user +
//                '}';
//    }
}
