package com.azathoth.handlistserver.post.tag

import com.azathoth.handlistserver.post.Post
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity(name = "post_tag")
data class PostTag(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long = 0,
    var name: String,

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    var posts: MutableSet<Post> = HashSet()
)

