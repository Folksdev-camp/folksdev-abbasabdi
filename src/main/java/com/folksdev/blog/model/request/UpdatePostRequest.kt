package com.folksdev.blog.model.request

import javax.validation.constraints.NotBlank

data class UpdatePostRequest(

    @field:NotBlank(message = "title must not be blank")
    val title: String,

    @field:NotBlank(message = "content must not be blank")
    val content: String,

)
