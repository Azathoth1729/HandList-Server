package com.azathoth.handlistserver.model.task

import jakarta.transaction.Transactional
import org.springframework.data.repository.CrudRepository

interface TaskRepository : CrudRepository<Task, Long> {
    fun findByName(name: String): Set<Task>
    fun findBySpaceNodeId(nodeId: Long): Set<Task>
    fun findByAssignsEmail(email: String): Set<Task>
    @Transactional
    fun deleteBySpaceNodeId(nodeId: Long): Long
}