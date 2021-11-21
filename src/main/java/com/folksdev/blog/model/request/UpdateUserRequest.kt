package com.folksdev.blog.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UpdateUserRequest(

    @field:NotBlank(message = "username must not be blank")
    val username: String,

    @field:NotNull(message = "firstName must not be null")
    val firstName: String,

    @field:NotBlank(message = "lastName must not be blank")
    val lastName: String,

    @field:NotBlank(message = "email must not be blank")
    val email: String,

)
