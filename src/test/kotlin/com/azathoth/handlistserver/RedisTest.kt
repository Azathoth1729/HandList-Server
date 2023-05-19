package com.azathoth.handlistserver

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import java.util.LinkedHashMap

@SpringBootTest
class RedisTest(
    @Autowired private val redisTemplate: RedisTemplate<String, Any>,
) {

    data class Person(val name: String, val age: Int)

    @Test
    fun test() {
        redisTemplate.opsForValue().set("username", "miku")
        assertEquals("miku", redisTemplate.opsForValue().get("username"))

        redisTemplate.opsForValue().set("user:1", Person("miku", 26))
        val person = redisTemplate.opsForValue().get("user:1")  as LinkedHashMap<*, *>
        println(person["name"])
//        assertEquals(Person("miku", 26), person)
    }
}