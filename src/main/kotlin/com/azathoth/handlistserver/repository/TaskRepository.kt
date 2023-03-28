package com.azathoth.handlistserver.repository

import com.azathoth.handlistserver.model.task.Task
import jakarta.transaction.Transactional
import org.springframework.data.repository.CrudRepository

interface TaskRepository : CrudRepository<Task, Long> {
    fun findByName(name: String): Set<Task>
    fun findBySpaceNodeId(node_id: Long): Set<Task>

    @Transactional
    fun deleteBySpaceNodeId(node_id: Long): Long
}