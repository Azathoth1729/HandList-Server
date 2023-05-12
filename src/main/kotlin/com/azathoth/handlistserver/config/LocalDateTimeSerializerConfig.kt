package com.azathoth.handlistserver.config


import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Configuration
class LocalDateTimeSerializerConfig {
    @Value("\${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private val pattern: String? = null


    @Bean
    fun localDateTimeSerializer() =
        LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));

    @Bean
    fun localDateTimeDeserializer() =
        LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern))


    @Bean
    fun jackson2ObjectMapperBuilderCustomizer(): Jackson2ObjectMapperBuilderCustomizer {
        return Jackson2ObjectMapperBuilderCustomizer { builder ->
            builder.serializerByType(
                LocalDateTime::class.java, localDateTimeSerializer()
            )
            builder.deserializerByType(
                LocalDateTime::class.java, localDateTimeDeserializer()
            )
        }
    }

}