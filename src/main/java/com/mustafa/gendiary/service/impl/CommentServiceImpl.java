package com.mustafa.gendiary.service.impl;

import com.mustafa.gendiary.dto.CommentDto;
import com.mustafa.gendiary.model.Comment;
import com.mustafa.gendiary.model.User;
import com.mustafa.gendiary.repository.CommentRepository;
import com.mustafa.gendiary.repository.PostRepository;
import com.mustafa.gendiary.service.CommentService;
import com.mustafa.gendiary.service.UserService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserService userService;
    public CommentServiceImpl(CommentRepository commentRepository,
                              PostRepository postRepository,
                              UserService userService) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Override
    public List<CommentDto> getAllComment() {
        return commentRepository.findAll().stream()
                .map(comment ->CommentDto.builder()
                        .commenterUUID(comment.getUuid())
                        .commentContent(comment.getCommentUUID())
                        .publishedDate(comment.getPublishedDate())
                        .countOfLike(comment.getCountOfLike())
                        .postUUID(comment.getUuid())
                        .likeList(comment.getLikeList())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.map(x -> CommentDto.builder()
                .commenterUUID(x.getUuid())
                .commentContent(x.getCommentUUID())
                .publishedDate(x.getPublishedDate())
                .countOfLike(x.getCountOfLike())
                .postUUID(x.getUuid())
                .likeList(x.getLikeList()).build()).
                orElseThrow(() -> new RuntimeException("Comment not found."));
    }

    @Override
    public String createComment(CommentDto commentDto) {
        Comment comment = Comment.builder()
                .commentUUID(commentDto.getCommenterUUID())
                .commentContent(commentDto.getCommentContent())
                .publishedDate(commentDto.getPublishedDate())
                .countOfLike(commentDto.getCountOfLike())
                .post(postRepository.findByUuid(commentDto.getPostUUID()))
                .likeList(commentDto.getLikeList())
                .build();
        commentRepository.save(comment);
        return "Successfully";
    }

    @Override
    public String updateComment(Long id, CommentDto commentDto) {
        return null;
    }

    @Override
    public String deleteComment(Long id) {
        commentRepository.deleteById(id);
        return "Successfully";
    }

    @Override
    public Comment getCommentByUuid(Long uuid) {
        return commentRepository.findByUuid(String.valueOf(uuid));
    }

    @Override
    public Comment getLikeComment(Long uuid) {
        User authUser = userService.getAuthenticatedUser();
        Comment targetComment = getCommentByUuid(uuid);
        if (!targetComment.getLikeList().contains(authUser)) {
            targetComment.setCountOfLike(targetComment.getCountOfLike()+1);
            targetComment.getLikeList().add(authUser);
            targetComment.setDateLastModified((Timestamp) new Date());
            Comment updatedComment = commentRepository.save(targetComment);
            return updatedComment;
        } else {
            return null;
        }

    }

    @Override
    public Comment getUnlikeComment(Long uuid) {
        User authUser = userService.getAuthenticatedUser();
        Comment targetComment = getCommentByUuid(uuid);
        if (!targetComment.getLikeList().contains(authUser)) {
            targetComment.setCountOfLike(targetComment.getCountOfLike()-1);
            targetComment.getLikeList().remove(authUser);
            targetComment.setDateLastModified((Timestamp) new Date());
            Comment updatedComment = commentRepository.save(targetComment);
            return updatedComment;
        } else {
            return null;
        }    }
}
