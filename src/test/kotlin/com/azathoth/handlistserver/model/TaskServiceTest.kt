package com.azathoth.handlistserver.model

import com.azathoth.handlistserver.model.task.Task
import com.azathoth.handlistserver.repository.TaskRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TaskServiceTest(@Autowired val taskRepo: TaskRepository) {
    @Test
    fun addTaskTest() {
//        val newTask = Task(name="Game")
    }
}