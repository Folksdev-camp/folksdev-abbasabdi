package com.folksdev.blog.controller;


import com.folksdev.blog.model.dto.CommentDto;
import com.folksdev.blog.model.request.CreateCommentRequest;
import com.folksdev.blog.model.request.UpdateCommentRequest;
import com.folksdev.blog.service.CommentService;
import com.folksdev.blog.service.PostService;
import com.folksdev.blog.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final PostService postService;

    public CommentController(CommentService commentService, UserService userService, PostService postService) {
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(){
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable String id){
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @PostMapping
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CreateCommentRequest createCommentRequest){
        return ResponseEntity.ok(commentService.createComment(createCommentRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@Valid @RequestBody UpdateCommentRequest updateCommentRequest, @PathVariable String id){
        return ResponseEntity.ok(commentService.updateComment(updateCommentRequest,id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable String id){
        return ResponseEntity.ok(commentService.deleteComment(id));
    }


}
