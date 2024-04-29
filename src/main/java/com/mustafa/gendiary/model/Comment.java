package com.mustafa.gendiary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "comment_uuid")
    private String commentUUID;

    @Column(name = "published_date")
    private Timestamp publishedDate;

    @Column(name = "last_modified_date")
    private Timestamp dateLastModified;

    @Column(name = "like_count")
    private Long countOfLike;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "comment_likes",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> likeList;
}
