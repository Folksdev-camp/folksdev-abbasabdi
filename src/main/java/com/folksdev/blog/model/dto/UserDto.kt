package com.folksdev.blog.model.dto

import com.fasterxml.jackson.annotation.JsonInclude


data class UserDto @JvmOverloads constructor(

    val id: String?,

    val username: String,

    val firstName: String,

    val lastName: String,

    val email: String,

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val post:List<PostDto>? = ArrayList(),

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    val commentDto: List<CommentDto>? = ArrayList(),

)
