package com.azathoth.handlistserver.model.task

import com.azathoth.handlistserver.model.task.Task
import jakarta.transaction.Transactional
import org.springframework.data.repository.CrudRepository

interface TaskRepository : CrudRepository<Task, Long> {
    fun findByName(name: String): Set<Task>
    fun findBySpaceNodeId(nodeId: Long): Set<Task>

    @Transactional
    fun deleteBySpaceNodeId(nodeId: Long): Long
}