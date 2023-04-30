package com.azathoth.handlistserver


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HandListServer

fun main(args: Array<String>) {
    runApplication<HandListServer>(*args)
}
