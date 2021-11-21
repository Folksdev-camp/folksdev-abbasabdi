package com.folksdev.blog.model.converter;

import com.folksdev.blog.entity.User;
import com.folksdev.blog.model.dto.CommentDto;
import com.folksdev.blog.model.dto.PostDto;
import com.folksdev.blog.model.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convert(User user){
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),

                user.getPosts()
                  .stream()
                  .map(post -> new PostDto(post.getId(),post.getTitle(),post.getContent(),post.getCreatedDate(),post.getUpdatedDate()))
                  .collect(Collectors.toList()),

                user.getComments()
                    .stream()
                    .map(comment -> new CommentDto(comment.getId(),comment.getText(),comment.getCreatedDate(),comment.getUpdatedDate()))
                    .collect(Collectors.toList())
            );

    }

    public List<UserDto> convertToUserDtoList(List<User> users){

        return users
                .stream()
                .map(this::convert)
                .collect(Collectors.toList());

    }

}
