package com.azathoth.handlistserver.post.tag

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PostTagService(private val postTagRepo: PostTagRepo) {
    fun findById(id: Long) = postTagRepo.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
}