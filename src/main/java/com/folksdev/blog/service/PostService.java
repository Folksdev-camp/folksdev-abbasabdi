package com.folksdev.blog.service;

import com.folksdev.blog.entity.Post;
import com.folksdev.blog.entity.User;
import com.folksdev.blog.exception.PostNotFoundException;
import com.folksdev.blog.model.converter.PostDtoConverter;
import com.folksdev.blog.model.converter.UserDtoConverter;
import com.folksdev.blog.model.converter.UserEntityConverter;
import com.folksdev.blog.model.dto.PostDto;
import com.folksdev.blog.model.request.CreatePostRequest;
import com.folksdev.blog.model.request.UpdatePostRequest;
import com.folksdev.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostDtoConverter postDtoConverter;
    private final UserService userService;
    private final UserEntityConverter userEntityConverter;

    public PostService(PostRepository postRepository, PostDtoConverter postDtoConverter, UserService userService, UserEntityConverter userEntityConverter) {
        this.postRepository = postRepository;
        this.postDtoConverter = postDtoConverter;
        this.userService = userService;
        this.userEntityConverter = userEntityConverter;
    }


    public List<PostDto> getAllPosts(){

        return postRepository.findAll()
                .stream()
                .map(post -> postDtoConverter.convert(post))
                .collect(Collectors.toList());

    }

    public PostDto getPostById(String id){
        return postDtoConverter.convert(findPostById(id));
    }

    public PostDto createPost(CreatePostRequest createPostRequest){

        User user = userEntityConverter.convert(userService.getUserById(createPostRequest.getUserId()));

        Post post = new Post(createPostRequest.getTitle(),createPostRequest.getContent(),user);

        return postDtoConverter.convert(postRepository.save(post));

    }

    public PostDto updatePost(UpdatePostRequest updatePostRequest , String id){

        Post post = findPostById(id);
        User user = userEntityConverter.convert(userService.getUserById(post.getUser().getId()));

        Post updatedPost = new Post(
                post.getId(),
                updatePostRequest.getTitle(),
                updatePostRequest.getContent(),
                post.getUser(),
                post.getComments()
        );

        return postDtoConverter.convert(postRepository.save(updatedPost));

    }

    public String deletePost(String id){
        Post post = findPostById(id);
        postRepository.delete(post);
        return "post with this "+id+" is deleted!";
    }


    public Post findPostById(String id){
        return postRepository.findById(id).orElseThrow(()->new PostNotFoundException("post not found with this "+id+" id"));
    }

}
