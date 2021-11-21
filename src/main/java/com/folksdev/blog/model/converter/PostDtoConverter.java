package com.folksdev.blog.model.converter;


import com.folksdev.blog.entity.Post;
import com.folksdev.blog.model.dto.CommentDto;
import com.folksdev.blog.model.dto.PostDto;
import com.folksdev.blog.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDtoConverter {

    public PostDto convert(Post post){

        return new PostDto(

                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getCreatedDate(),
                post.getUpdatedDate(),
                new UserDto(
                        post.getUser().getId(),
                        post.getUser().getUsername(),
                        post.getUser().getFirstName(),
                        post.getUser().getLastName(),
                        post.getUser().getEmail()
                ),
                post.getComments()
                        .stream()
                        .map(comment -> new CommentDto(
                                comment.getId(),
                                comment.getText(),
                                comment.getCreatedDate(),
                                comment.getUpdatedDate()
                        ) )
                        .collect(Collectors.toList())
        );

    }

    public List<PostDto> convertToPostDtoList(List<Post> posts){

        return posts
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());

    }


}
