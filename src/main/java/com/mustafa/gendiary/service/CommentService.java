package com.mustafa.gendiary.service;

import com.mustafa.gendiary.dto.CommentDto;
import com.mustafa.gendiary.model.Comment;

import java.util.List;

public interface CommentService {
    List<CommentDto> getAllComment();
    CommentDto getCommentById(Long id);
    String createComment(CommentDto commentDto);
    String updateComment(Long id, CommentDto commentDto);
    String deleteComment(Long id);
    Comment getCommentByUuid(Long id);
    Comment getLikeComment(Long id);
    Comment getUnlikeComment(Long id);

}
