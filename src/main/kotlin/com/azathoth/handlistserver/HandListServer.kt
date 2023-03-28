package com.azathoth.handlistserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

object AppConfig {
    const val API_VERSION = "v1"
}

@SpringBootApplication
class HandListServer

fun main(args: Array<String>) {
    runApplication<HandListServer>(*args)
}
