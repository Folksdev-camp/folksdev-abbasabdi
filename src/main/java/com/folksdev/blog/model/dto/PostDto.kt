package com.folksdev.blog.model.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

data class PostDto @JvmOverloads constructor(

    val id: String,

    val title: String,

    val content: String,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val userDto: UserDto? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val commentDto: List<CommentDto>? = null,

)
