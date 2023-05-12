package com.azathoth.handlistserver.post

import org.springframework.data.repository.CrudRepository

interface PostRepo:CrudRepository<Post, Long> {
}