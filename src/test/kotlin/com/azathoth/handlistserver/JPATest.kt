package com.azathoth.handlistserver

import com.azathoth.handlistserver.task.TaskRepo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
class JPATest(@Autowired private val taskRepo: TaskRepo) {
    @Test
    @Transactional(readOnly = true)
    fun taskFindAllTest() {
        taskRepo.findAll().first().let { println(it) }
    }
}