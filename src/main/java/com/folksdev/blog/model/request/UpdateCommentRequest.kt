package com.folksdev.blog.model.request

import javax.validation.constraints.NotBlank

data class UpdateCommentRequest(

    @field:NotBlank(message = "text must not be blank")
    val text: String,

)
