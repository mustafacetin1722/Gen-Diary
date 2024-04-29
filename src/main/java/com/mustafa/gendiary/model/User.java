package com.mustafa.gendiary.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mustafa.gendiary.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", unique = true)
    private String uuid;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "country")
    private String country;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "birt_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birtDate;

    @Column(name = "join_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:dd")
    private Date joinDate;

    @Column(name = "password")
    private String password;

    @Column(name = "shared_post_url")
    private String sharedPostUrl;

    @Column(name = "liked_comments")
    private String likedComments;

    @Column(name = "liked_posts")
    private String likedPosts;

    @Column(name = "follower_count")
    private Integer followerCount;

    @Column(name = "following_count")
    private Integer followingCount;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;



}
