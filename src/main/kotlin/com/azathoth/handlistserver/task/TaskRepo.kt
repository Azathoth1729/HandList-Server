package com.azathoth.handlistserver.task

import com.azathoth.handlistserver.task.model.Task
import jakarta.transaction.Transactional
import org.springframework.data.repository.CrudRepository

interface TaskRepo : CrudRepository<Task, Long> {
    fun findByName(name: String): Set<Task>
    fun findBySpaceNodeId(nodeId: Long): Set<Task>
    fun findByAssignsEmail(email: String): Set<Task>
    fun findByAssignsId(id: Long): Set<Task>

    @Transactional
    fun deleteTasksBySpaceNodeId(nodeId: Long): Long
}