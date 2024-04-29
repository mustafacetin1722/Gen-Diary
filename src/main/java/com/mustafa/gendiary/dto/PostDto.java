package com.mustafa.gendiary.dto;

import com.mustafa.gendiary.model.Comment;
import com.mustafa.gendiary.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    private String content;
    private MultipartFile uploadedImage;
    private MultipartFile renderedImage;
    private Integer likeCount;
    private Integer commentCount;
    private String likerUUIDS;
    private Timestamp dateCreated;
    private String postOwnerUUID;
    private List<Comment> postComments;
    private List<Tag> postTags;
}
