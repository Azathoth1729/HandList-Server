package com.azathoth.handlistserver.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializer

@Configuration
class RedisConfig {
    private inline fun <reified T> getRedisTemplate(connFactory: RedisConnectionFactory): RedisTemplate<String, T> {
        val redisTemplate = RedisTemplate<String, T>()
        val jsonSerializer = Jackson2JsonRedisSerializer(T::class.java)

        redisTemplate.setConnectionFactory(connFactory)

        // key
        redisTemplate.keySerializer = RedisSerializer.string()
        redisTemplate.hashKeySerializer = RedisSerializer.string()

        // value
        redisTemplate.valueSerializer = jsonSerializer
        redisTemplate.hashKeySerializer = jsonSerializer
        return redisTemplate
    }

    @Bean
    fun redisTemplate(connFactory: RedisConnectionFactory): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        val jsonSerializer = Jackson2JsonRedisSerializer(Any::class.java)

        redisTemplate.setConnectionFactory(connFactory)

        // key
        redisTemplate.keySerializer = RedisSerializer.string()
        redisTemplate.hashKeySerializer = RedisSerializer.string()

        // value
        redisTemplate.valueSerializer = jsonSerializer
        redisTemplate.hashKeySerializer = jsonSerializer
        return redisTemplate
    }

    @Bean
    fun aTemplate(connFactory: RedisConnectionFactory) = getRedisTemplate<String>(connFactory)
}