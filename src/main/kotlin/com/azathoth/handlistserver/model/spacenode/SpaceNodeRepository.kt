package com.azathoth.handlistserver.model.spacenode

import com.azathoth.handlistserver.model.spacenode.SpaceNode
import org.springframework.data.repository.CrudRepository

interface SpaceNodeRepository : CrudRepository<SpaceNode, Long> {
    fun findByPath(path: String): List<SpaceNode>
    fun deleteByPath(path: String): Long
    fun existsSpaceNodeByPath(path: String): Boolean
}