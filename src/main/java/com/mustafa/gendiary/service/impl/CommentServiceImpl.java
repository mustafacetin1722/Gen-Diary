package com.mustafa.gendiary.service.impl;

import com.mustafa.gendiary.dto.CommentDto;
import com.mustafa.gendiary.model.Comment;
import com.mustafa.gendiary.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {
    @Override
    public List<CommentDto> getAllComment() {
        return null;
    }

    @Override
    public CommentDto getCommentById(Long id) {
        return null;
    }

    @Override
    public String createComment(CommentDto commentDto) {
        return null;
    }

    @Override
    public String updateComment(Long id, CommentDto commentDto) {
        return null;
    }

    @Override
    public String deleteComment(Long id) {
        return null;
    }

    @Override
    public Comment getCommentByUuid(Long id) {
        return null;
    }

    @Override
    public Comment getLikeComment(Long id) {
        return null;
    }

    @Override
    public Comment getUnlikeComment(Long id) {
        return null;
    }
}
