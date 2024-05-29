package com.mustafa.gendiary.controller;

import com.mustafa.gendiary.dto.PostDto;
import com.mustafa.gendiary.model.Post;
import com.mustafa.gendiary.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPost(){
        return new ResponseEntity<>(postService.getAllPost(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getByIdPost(@PathVariable Long id){
        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }
    public ResponseEntity<String> createPost(Long id ,PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
    }
}
