package com.azathoth.handlistserver.post.tag

import org.springframework.data.repository.CrudRepository

interface PostTagRepo: CrudRepository<PostTag, Long> {
}