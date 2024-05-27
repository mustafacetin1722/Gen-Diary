package com.mustafa.gendiary.service.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mustafa.gendiary.dto.PostDto;
import com.mustafa.gendiary.model.Post;
import com.mustafa.gendiary.model.User;
import com.mustafa.gendiary.repository.PostRepository;
import com.mustafa.gendiary.service.PostService;
import com.mustafa.gendiary.service.UserService;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;


import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {
    @Value("${ai-service.process-url}")
    private String aiServiceProcessUrl;
    private final PostRepository postRepository;
    private final UserService userService;
    private final RestTemplate restTemplate;
    private final Environment environment;

    public PostServiceImpl(PostRepository postRepository,
                           UserService userService,
                           RestTemplate restTemplate,
                           Environment environment) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    @Override
    public List<PostDto> getAllPost() {
        return postRepository.findAll().stream()
                .map(post -> PostDto.builder()
                        .content(post.getContent())
                        .likeCount(post.getLikeCount())
                        .commentCount(post.getCommentCount())
                        .dateCreated(post.getDateCreated())
                        .postOwnerUUID(post.getPostOwnerUuid())
                        .postComments(post.getPostComments())
                        .postTags(post.getPostTags())
                        .build()).collect(Collectors.toList());

    }

    @Override
    public PostDto getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.map(x -> PostDto.builder()
                .content(x.getContent())
                .likeCount(x.getLikeCount())
                .commentCount(x.getCommentCount())
                .dateCreated(x.getDateCreated())
                .postOwnerUUID(x.getPostOwnerUuid())
                .postComments(x.getPostComments())
                .postTags(x.getPostTags())
                .build()).orElseGet(() -> {
                    throw new RuntimeException("User not found.");
                });
    }

    @Override
    public String createPost(PostDto postDto) {
        User authUser = userService.getAuthenticatedUser();
        Post newPost = new Post();
        newPost.setContent(postDto.getContent());
        newPost.setUser(authUser);
        newPost.setLikeCount(0);
        newPost.setPostComments(null);
        if (postDto.getUploadedImage()!=null){
        }
        return null;
    }
   /* private String processImageWithAIService(MultipartFile uploadedImage) throws IOException {
        byte[] imageBytes = uploadedImage.getBytes();
        String encodedImage = Base64.getEncoder().encodeToString(imageBytes);

        HttpPost httpPost = new HttpPost(aiServiceProcessUrl);
        String jsonPayload = "{\"image\": \"" + encodedImage + "\"}";
        StringEntity entity = new StringEntity(jsonPayload);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");


        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {

            String responseString = EntityUtils.toString(response.getEntity());
            JSONPObject jsonResponse = new JsonParser().parse(responseString).getAsJsonObject();
            String encodedProcessedImage = jsonResponse.get("processed_image").getAsString();
            byte[] decodedBytes = Base64.getDecoder().decode(encodedProcessedImage);
            return saveDecodedImage(decodedBytes);
        }
    }*/
    private String saveDecodedImage(byte[] imageBytes) throws IOException {
        String uploadDir = environment.getProperty("upload.rendered.images");
        String imageName = "rendered_" + UUID.randomUUID() + ".jpg";
        String imagePath = uploadDir + File.separator + imageName;

        try (OutputStream out = new FileOutputStream(imagePath)) {
            out.write(imageBytes);
        }
        return environment.getProperty("app.root.backend") + File.separator + uploadDir + File.separator + imageName;
    }

    @Override
    public String updatePost(Long id, PostDto postDto) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isEmpty()){
            throw new RuntimeException("Post not found.");
        }
        post.get().setContent(postDto.getContent());
        post.get().setLikeCount(postDto.getLikeCount());
        post.get().setCommentCount(postDto.getCommentCount());
        return "Successfully";
    }
    @Override
    public String deletePost(Long id) {
        postRepository.deleteById(id);
        return "Successfully";
    }
}
