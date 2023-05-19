package com.azathoth.handlistserver.spacenode

import com.azathoth.handlistserver.spacenode.model.SpaceNode
import org.springframework.data.repository.CrudRepository

interface SpaceNodeRepo : CrudRepository<SpaceNode, Long> {
    fun findByPath(path: String): SpaceNode
    fun deleteByPath(path: String): Long
    fun existsSpaceNodeByPath(path: String): Boolean
}