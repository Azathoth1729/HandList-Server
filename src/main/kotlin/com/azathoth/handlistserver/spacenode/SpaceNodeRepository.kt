package com.azathoth.handlistserver.spacenode

import com.azathoth.handlistserver.spacenode.model.SpaceNode
import org.springframework.data.repository.CrudRepository

interface SpaceNodeRepository : CrudRepository<SpaceNode, Long> {
    fun findByPath(path: String): List<SpaceNode>
    fun deleteByPath(path: String): Long
    fun existsSpaceNodeByPath(path: String): Boolean
}