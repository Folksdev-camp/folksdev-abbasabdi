package com.folksdev.blog.service;


import com.folksdev.blog.entity.Comment;
import com.folksdev.blog.entity.Post;
import com.folksdev.blog.entity.User;
import com.folksdev.blog.exception.CommentNoFoundException;
import com.folksdev.blog.model.converter.CommentDtoConverter;
import com.folksdev.blog.model.dto.CommentDto;
import com.folksdev.blog.model.request.CreateCommentRequest;
import com.folksdev.blog.model.request.UpdateCommentRequest;
import com.folksdev.blog.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentDtoConverter commentDtoConverter;

    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, CommentDtoConverter commentDtoConverter, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.commentDtoConverter = commentDtoConverter;
        this.userService = userService;
        this.postService = postService;
    }

    public List<CommentDto> getAllComments(){

        return commentRepository.findAll()
                .stream()
                .map(comment -> commentDtoConverter.convert(comment))
                .collect(Collectors.toList());

    }

    public CommentDto getCommentById(String id){
        return commentDtoConverter.convert(findCommentById(id));
    }

    public CommentDto createComment(CreateCommentRequest createCommentRequest){

        User user = userService.findUserById(createCommentRequest.getUserId());
        Post post = postService.findPostById(createCommentRequest.getPostId());

        Comment comment = new Comment(createCommentRequest.getText(),user,post);

        return commentDtoConverter.convert(commentRepository.save(comment));

    }

    public CommentDto updateComment(UpdateCommentRequest updateCommentRequest, String id){

        Comment comment = findCommentById(id);
        Comment updatedComment = new Comment(

                comment.getId(),
                updateCommentRequest.getText(),
                comment.getUser(),
                comment.getPost(),
                comment.getCreatedDate(),
                comment.getUpdatedDate()
        );
        return commentDtoConverter.convert(commentRepository.save(updatedComment));

    }

    public String deleteComment(String id){

        Comment foundComment = findCommentById(id);
        commentRepository.delete(foundComment);
        return "comment with this "+id+" is deleted";

    }




    public Comment findCommentById(String id){
        return commentRepository.findById(id).orElseThrow(()->new CommentNoFoundException("comment with this "+id+" not found!"));
    }



}
