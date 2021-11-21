package com.folksdev.blog.model.request

import javax.validation.constraints.NotBlank

data class CreatePostRequest(

    @field:NotBlank(message = "title must not be blank")
    val title: String,

    @field:NotBlank(message = "content must not be blank")
    val content: String,

    @field:NotBlank(message = "userId must not be blank")
    val userId: String
)
