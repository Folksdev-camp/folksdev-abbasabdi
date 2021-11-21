package com.folksdev.blog.controller;


import com.folksdev.blog.model.dto.PostDto;
import com.folksdev.blog.model.request.CreatePostRequest;
import com.folksdev.blog.model.request.UpdatePostRequest;
import com.folksdev.blog.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable String id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody CreatePostRequest createPostRequest){
        return ResponseEntity.ok(postService.createPost(createPostRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @PathVariable String id, @RequestBody UpdatePostRequest updatePostRequest){
        return ResponseEntity.ok(postService.updatePost(updatePostRequest,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable String id){
        return ResponseEntity.ok(postService.deletePost(id));
    }

}
