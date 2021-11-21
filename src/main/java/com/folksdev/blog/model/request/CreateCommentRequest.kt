package com.folksdev.blog.model.request

import javax.validation.constraints.NotBlank

data class CreateCommentRequest(

    @field:NotBlank(message = "text must not be blank")
    val text: String,

    @field:NotBlank(message = "userId must not be blank")
    val userId: String,

    @field:NotBlank(message = "postId must not be blank")
    val postId: String

)
