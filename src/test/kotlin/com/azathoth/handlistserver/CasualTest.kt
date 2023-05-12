package com.azathoth.handlistserver

import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CasualTest {
    @Test
    fun dateTest() {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val ldt = LocalDateTime.parse("2023-03-17", formatter)
        println(ldt)
    }

    @Test
    fun dataTest() {
        println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
    }
}