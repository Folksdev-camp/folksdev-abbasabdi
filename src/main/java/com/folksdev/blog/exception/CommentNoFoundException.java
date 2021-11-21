package com.folksdev.blog.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentNoFoundException extends RuntimeException{

    public CommentNoFoundException(String message) {
        super(message);
    }
}
