package com.folksdev.blog.model.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

data class CommentDto @JvmOverloads constructor(

    val id: String?,

    val text: String,

    val createdDate: LocalDateTime,

    val updatedDate: LocalDateTime,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val userDto: UserDto? = null,

    )
