package com.mustafa.gendiary.service;

import com.mustafa.gendiary.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPost();
    PostDto getPostById(Long id);
    String createPost(PostDto postDto);
    String updatePost(Long id, PostDto postDto);
    String deletePost(Long id);
}
