package com.azathoth.handlistserver.spacenode

import com.azathoth.handlistserver.spacenode.model.SpaceNode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException

@Service
class SpaceNodeService(@Autowired val spaceNodeRepo: SpaceNodeRepo) {

    fun findAll() = spaceNodeRepo.findAll()

    fun findByPath(path: String) = spaceNodeRepo.findByPath(path)

    fun findById(id: Long): SpaceNode =
        spaceNodeRepo.findByIdOrNull(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun insert(node: SpaceNode): SpaceNode = spaceNodeRepo.save(node)

    fun insertAll(nodes: Iterable<SpaceNode>) = spaceNodeRepo.saveAll(nodes)

    fun updateById(id: Long, node: SpaceNode): SpaceNode =
        if (spaceNodeRepo.existsById(id)) {
            node.id = id
            spaceNodeRepo.save(node)
        } else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    fun deleteById(id: Long) =
        if (spaceNodeRepo.existsById(id)) spaceNodeRepo.deleteById(id)
        else throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @Transactional
    fun deleteSubPathsOf(path: String) =
        spaceNodeRepo.findAll().filter { it.path.startsWith(path) }.forEach {
            deleteById(it.id)
        }

    @Transactional
    fun deleteSubPathsById(id: Long) = run {
        val node = findById(id)
        spaceNodeRepo.findAll().filter { it.path.startsWith(node.path) }.forEach {
            deleteById(it.id)
        }
    }

}