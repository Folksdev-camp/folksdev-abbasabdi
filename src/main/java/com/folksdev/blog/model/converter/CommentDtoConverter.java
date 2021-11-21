package com.folksdev.blog.model.converter;


import com.folksdev.blog.entity.Comment;
import com.folksdev.blog.model.dto.CommentDto;
import com.folksdev.blog.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentDtoConverter {

    public CommentDto convert(Comment comment){

        return new CommentDto(

                comment.getId(),
                comment.getText(),
                comment.getCreatedDate(),
                comment.getUpdatedDate(),
                new UserDto(
                        comment.getUser().getId(),
                        comment.getUser().getUsername(),
                        comment.getUser().getFirstName(),
                        comment.getUser().getLastName(),
                        comment.getUser().getEmail()
                )

        );

    }

    public List<CommentDto> convertToCommentDtoList(List<Comment> comments){
        return comments
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

}
