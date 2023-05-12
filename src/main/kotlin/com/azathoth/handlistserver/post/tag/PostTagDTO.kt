package com.azathoth.handlistserver.post.tag

data class PostTagDTO(
    val id: Long?,
    val name: String?,
)

fun PostTag.toDTO(): PostTagDTO = PostTagDTO(
    id = id,
    name = name
)