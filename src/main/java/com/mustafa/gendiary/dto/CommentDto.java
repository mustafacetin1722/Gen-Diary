package com.mustafa.gendiary.dto;

import com.mustafa.gendiary.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private String commenterUUID;
    private String commentContent;
    private Timestamp publishedDate;
    private Long countOfLike;
    private String postUUID;
    private List<User> likeList;
}
